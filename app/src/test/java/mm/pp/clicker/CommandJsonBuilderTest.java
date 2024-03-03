package mm.pp.clicker;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Test;

import mm.pp.clicker.master.lib.CommandJsonBuilder;

public class CommandJsonBuilderTest {
    @Test
    public void test() throws JsonProcessingException {
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
    }
}
