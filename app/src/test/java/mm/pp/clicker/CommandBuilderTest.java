package mm.pp.clicker;

import org.junit.Test;

import mm.pp.clicker.tools.CommandBuilder;

public class CommandBuilderTest {
    @Test
    public void test(){
        CommandBuilder commandBuilder=new CommandBuilder();

        commandBuilder=commandBuilder
                .click(1,2)
                .sleep(100)
                .swipe(100,200,300,400)
                .swipe(100,200,300,400,500)
                .swipe(100,200,300,400,500,1000)
        ;

        String out=commandBuilder.build();


        System.out.println(out);// click 1 2;sleep 100;swipe 100 200 300 400;swipe 100 200 300 400 500;swipe 100 200 300 400 500 1000;
    }
}
