# Clicker client app

This is a remote click app.

It uses HTTP to control your device.

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

Except following files, all files is under GPL-3.0

1. mm.pp.clicker.tools.**  -> The MIT LICENSE

# Use CommandJsonBuilder and CommandBuilder

Example can be find in [Builder example](https://github.com/aaadddfgh/Android-Remote-Clicker/blob/main/app/src/test/java/mm/pp/clicker/).
``` java
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

