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

package mm.pp.clicker.master.lib;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.TreeMap;

import mm.pp.clicker.master.lib.tools.CommandBuilder;

public class CommandJsonBuilder {

    CommandBuilder commandBuilder;

    public CommandJsonBuilder(){
        this.commandBuilder=new CommandBuilder();
    }

    public CommandJsonBuilder click(int x, int y) {
        commandBuilder.click(x, y);
        return this;
    }

    // 添加睡眠命令到构建器
    public CommandJsonBuilder sleep(int duration) {
        commandBuilder.sleep(duration);
        return this;
    }

    // 添加滑动命令到构建器，不带延迟
    public CommandJsonBuilder swipe(int x1, int y1, int x2, int y2) {
        commandBuilder.swipe(x1, y1, x2, y2);
        return this;
    }

    public CommandJsonBuilder swipe(int x1, int y1, int x2, int y2, int duration) {
        commandBuilder.swipe(x1, y1, x2, y2, duration);
        return this;
    }

    // 添加滑动命令到构建器，带持续时间和延迟
    public CommandJsonBuilder swipe(int x1, int y1, int x2, int y2, int duration, int delay) {
        commandBuilder.swipe(x1, y1, x2, y2, duration, delay);
        return this;
    }

    public void clear() {
        commandBuilder.clear();
        return;
    }

    public String buildString() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        CommandDTO commandDTO=new CommandDTO();
        commandDTO.commands=commandBuilder.build();
        return objectMapper.writeValueAsString(commandDTO);
    }

}