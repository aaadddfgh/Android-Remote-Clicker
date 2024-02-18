# clicker slave

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
