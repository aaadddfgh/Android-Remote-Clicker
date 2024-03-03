/**
 * The MIT License (MIT)
 * =====================
 * <p>
 * Copyright © aaadddfgh github.com/aaadddfgh
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mm.pp.clicker.master.lib.tools;

import mm.pp.clicker.tools.Command;

import java.util.ArrayList;

public class CommandBuilder {

    public CommandBuilder() {
        //this.commandArrayList=new ArrayList<>();
        this.stringBuilder = new StringBuilder();
    }

    private StringBuilder stringBuilder;

    public CommandBuilder click(int x, int y) {
        stringBuilder.append(makeClick(x, y)).append(';');
        return this;
    }

    // 添加睡眠命令到构建器
    public CommandBuilder sleep(int duration) {
        stringBuilder.append(makeSleep(duration)).append(';');
        return this;
    }

    // 添加滑动命令到构建器，不带延迟
    public CommandBuilder swipe(int x1, int y1, int x2, int y2) {
        stringBuilder.append(makeSwipe(x1, y1, x2, y2)).append(';');
        return this;
    }

    public CommandBuilder swipe(int x1, int y1, int x2, int y2, int duration) {
        stringBuilder.append(makeSwipe(x1, y1, x2, y2, duration)).append(';');
        return this;
    }

    // 添加滑动命令到构建器，带持续时间和延迟
    public CommandBuilder swipe(int x1, int y1, int x2, int y2, int duration, int delay) {
        stringBuilder.append(makeSwipe(x1, y1, x2, y2, duration, delay)).append(';');
        return this;
    }


    public String build() {
        return stringBuilder.toString();
    }

    public void clear() {
        stringBuilder.setLength(0);
        return;
    }

    public static Command makeClick(int x, int y) {
        return new Command(Command.Actions.CLICK).addPosition(x, y);
    }

    public static Command makeSleep(int duration) {
        return new Command(Command.Actions.SLEEP).setDuration(duration);
    }

    public static Command makeSwipe(int x1, int y1, int x2, int y2) {
        return new Command(Command.Actions.SWIPE).addPosition(x1, y1).addPosition(x2, y2);
    }

    public static Command makeSwipe(int x1, int y1, int x2, int y2, int duration) {
        return makeSwipe(x1, y1, x2, y2).setDuration(duration);
    }

    public static Command makeSwipe(int x1, int y1, int x2, int y2, int duration, int delay) {
        return makeSwipe(x1, y1, x2, y2, duration).setDelay(delay);
    }

    public static void main(String[] args) {
        // Example usage
        CommandBuilder commandBuilder=new CommandBuilder();

        commandBuilder=commandBuilder
                .click(1,2)
                .sleep(100)
        .swipe(100,200,300,400)
        .swipe(100,200,300,400,500)
        .swipe(100,200,300,400,500,1000)
        ;

        String out=commandBuilder.build();


        System.out.println(out);

    }
}