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

public class Command {
    public enum Actions {
        CLICK("click"), SLEEP("sleep"), SWIPE("swipe");
        public String name;

        Actions(String name) {
            this.name = name;
        }

        public static Actions getAction(String name) {
            for (Actions optionTypeEnum : values()) {
                if (optionTypeEnum.name.equals(name)) {
                    return optionTypeEnum;
                }
            }
            return null;

        }
    }
//
//    @Override
//    public boolean equals(@Nullable Object obj) {
//        if(obj.getClass()!=Command.class){
//            return false;
//        }
//        else {
//            return ((Command) obj).action.equals(this.action) &&
//                    ((Command) obj).action.equals(this.duration)&&
//                    ((Command) obj).action.equals(this.delay)&&
//                    ((Command) obj).action.equals(this.pos);
//        }
//    }

    public String action;
    public ArrayList<ActionPoint> pos;
    public Integer duration;
    public Integer delay;

    public Command() {
        this.pos = new ArrayList<>();
    }

    public Command(Actions actions) {
        this.action = actions.name;
        this.pos = new ArrayList<>();
    }

    public Command addPosition(int x, int y) {
        pos.add(new ActionPoint(x, y));
        return this;
    }

    public Command setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public Command setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(action);
        for (ActionPoint point : pos) {
            builder.append(" ").append(point.x).append(" ").append(point.y);
        }
        if (duration != null && duration > 0) {
            builder.append(" ").append(duration);
        }
        if (delay != null && delay > 0) {
            builder.append(" ").append(delay);
        }
        return builder.toString();
    }
}
