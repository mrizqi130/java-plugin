# User Guide

1. build jar file

```bash
./mvnw clean package
```

and the jar file will be generated in `target` folder, like `demo-0.0.1-SNAPSHOT.jar`.

2. confif the `ext-plugin` in `config.yaml` of APISIX

```yaml
ext-plugin:
  cmd: ['java', '-jar', '-Xmx1g', '-Xms1g', '/path/to/demo-0.0.1-SNAPSHOT.jar'] # d
```

NOTE: replace `/path/to/demo-0.0.1-SNAPSHOT.jar` to the real path of the jar file in your environment.

3. start APISIX, and add route with `ext-plugin-pre-req` plugin

```bash
curl -i http://127.0.0.1:9180/apisix/admin/routes/1  -H 'X-API-KEY: edd1c9f034335f136f87ad84b625c8f1' -X PUT -d '
{
    "uri": "/get",
    "plugins": {
        "ext-plugin-pre-req": {
            "conf" : [
                {"name": "DemoFilter", "value": "{\"enable\":\"feature\"}"}
            ]
        }
    },
    "upstream": {
        "type": "roundrobin",
        "nodes": {
            "httpbin.org:80": 1
        }
    }
}'
```

4. test the route

```bash
curl http://127.0.0.1:9080/get
{
  "args": {},
  "headers": {
    "Accept": "*/*",
    "Host": "127.0.0.1",
    "User-Agent": "curl/7.79.1",
    "X-Amzn-Trace-Id": "Root=1-634ec286-02980c910f0608973ea7d9df",
    "X-Forwarded-Host": "127.0.0.1"
  },
  "origin": "127.0.0.1, 103.152.220.40",
  "url": "http://127.0.0.1/get"
}
```

check the error log of APISIX, you will see the log like this:

```nginx
2022/10/18 23:13:09 [warn] 95270#82969626: *369 [lua] init.lua:955: 2022-10-18 23:13:09.828  WARN 95295 --- [ntLoopGroup-2-2] c.e.d.DemoFilter                         : DemoFilter is running
, context: ngx.timer
```

