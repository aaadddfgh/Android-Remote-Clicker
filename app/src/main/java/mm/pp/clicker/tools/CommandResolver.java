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
package mm.pp.clicker.tools;

import java.util.ArrayList;

import mm.pp.clicker.tools.Command.Actions;

public class CommandResolver {

    public static ArrayList<Command> build(String src) throws IllegalStateException , RuntimeException {
        ArrayList<Command> commands=new ArrayList<>();
        String[] parts = src.split(";\\s*");
        for (String s:parts) {
            commands.add(buildOne(s));
        }
        return commands;
    }

    public static Command buildOne(String src) throws IllegalStateException , RuntimeException {
        Command command = new Command();
        String[] parts = src.split(" ");

        switch (Actions.getAction(parts[0])) {
            case CLICK:
                command.action = "click";
                command.pos = new ArrayList<>();
                command.delay=0;
                if (parts.length == 3) {


                    command.pos.add(new ActionPoint(Integer.valueOf(parts[1]), Integer.valueOf(parts[2])));
                } else if (parts.length == 4) {
                    command.delay = Integer.valueOf(parts[3]);
                } else {
                    throw new IllegalStateException("Not a command: " + parts.toString());
                }
                break;

            case SLEEP:
                if (parts.length != 2) {
                    throw new IllegalStateException("Not a command: " + parts.toString());
                }
                command.action = "sleep";
                command.duration = Integer.valueOf(parts[1]);
                break;

            case SWIPE:
                command.action = "swipe";
                command.pos = new ArrayList<>();
                command.duration=1000;
                command.delay=0;
                switch (parts.length){
                    case 7:
                        command.delay=Integer.valueOf(parts[6]);
                    case 6:
                        command.duration=Integer.valueOf(parts[5]);
                    case 5:
                        command.pos.add(new ActionPoint(Integer.valueOf(parts[1]), Integer.valueOf(parts[2])));
                        command.pos.add(new ActionPoint(Integer.valueOf(parts[3]), Integer.valueOf(parts[4])));
                        break;
                    default:
                        throw new IllegalStateException("Not a command: " + parts.toString());
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + parts[0]);

        }
        return command;
    }
}
