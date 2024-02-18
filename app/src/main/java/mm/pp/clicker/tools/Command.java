package mm.pp.clicker.tools;

import android.graphics.Point;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Command {
    public enum  Actions{
        CLICK("click"),SLEEP("sleep"),SWIPE("swipe");
        public String name;
        Actions(String name) {
            this.name=name;
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
}
