# Clicker client app

This is a remote click app.

It uses HTTP to control your device.

If you can not root your device or use USB debug on old version android, you can consider this app.

# How to use

0. Make sure privilege

It use `Accessibility` and internet. Make sure accessibility on.

1. Start

You can click "开始" button to allow remote control.

Make POST request to yourDevicesIP:port (default 8080) with following body:
``` json
{
    "commands":"click 100 200;"
}
```
Your device should click at Point(x:100,y:200) automatically.

# Feature
1. Remote clicker via network without root or adb.
2. Auto start after reboot.
3. Auto wake before click.

# Example Commands

``` 
click 100 200;
sleep 1000;
swipe 100 200 300 400;
```
These commands simulate the following actions:

1. Click at the coordinates (100, 200).
2. Wait for 1000 ms.
3. Swipe from coordinates (100, 200) to coordinates (300, 400).

# LICENSE

Except following files, this software and all other files is under GPL-3.0

1. mm.pp.clicker.tools.**  -> The MIT LICENSE
2. mm.pp.clicker.master.lib.** -> The MIT LICENSE

# Use CommandJsonBuilder and CommandBuilder

You can directly copy `mm.pp.clicker.master.lib.**` to your project.  
Example can be find in [Builder example](https://github.com/aaadddfgh/Android-Remote-Clicker/blob/main/app/src/test/java/mm/pp/clicker/).
``` java

// CommandJsonBuilder needs "com.fasterxml.jackson.core:jackson-databind

CommandJsonBuilder commandJsonBuilder=new CommandJsonBuilder();
commandJsonBuilder
        .click(1,2)
        .sleep(100)
        .swipe(100,200,300,400)
        .swipe(100,200,300,400,500)
        .swipe(100,200,300,400,500,1000)
;

String out=commandJsonBuilder.buildString();
/**
 * Output string
 * {"commands":"click 1 2;sleep 100;swipe 100 200 300 400;swipe 100 200 300 400 500;swipe 100 200 300 400 500 1000;"}
 */


System.out.println(out);
```

``` java
CommandBuilder commandBuilder=new CommandBuilder();

commandBuilder=commandBuilder
        .click(1,2)
        .sleep(100)
        .swipe(100,200,300,400)
        .swipe(100,200,300,400,500)
        .swipe(100,200,300,400,500,1000)
;

String out=commandBuilder.build();
// click 1 2;sleep 100;swipe 100 200 300 400;swipe 100 200 300 400 500;swipe 100 200 300 400 500 1000;
```
## js/ts lib
Find here [npm](https://www.npmjs.com/package/clicker-master-lib) and [github](https://github.com/aaadddfgh/clicker-master-lib)
