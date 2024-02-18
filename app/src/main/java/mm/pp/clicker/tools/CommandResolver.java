package mm.pp.clicker.tools;

import android.graphics.Point;

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
