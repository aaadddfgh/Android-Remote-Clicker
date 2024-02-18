package mm.pp.clicker;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import mm.pp.clicker.dto.CommandDTO;
import mm.pp.clicker.tools.Command;
import mm.pp.clicker.tools.CommandResolver;

public class JsonCommandTest {
    @Test
    public void Test() throws IOException {
        ObjectMapper objectMapper =new ObjectMapper();

        CommandDTO commandDTO = objectMapper.readValue("{\"commands\":\"sleep 1000;swipe 100 2 3 4;\"}", CommandDTO.class);
        ArrayList<Command> array = CommandResolver.build(commandDTO.commands);
        return;
    }
}
