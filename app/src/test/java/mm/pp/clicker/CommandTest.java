package mm.pp.clicker;

import org.junit.Test;

import mm.pp.clicker.tools.CommandResolver;

import static org.junit.Assert.assertEquals;

public class CommandTest {
    @Test
    public void Test() {
        CommandResolver.buildOne("sleep 100");
        CommandResolver.buildOne("click 100 200");
        CommandResolver.buildOne("swipe 100 200 300 400  ");
        CommandResolver.build("sleep 100;\nclick 100 200;\nswipe 100 200 300 400;");
    }
}
