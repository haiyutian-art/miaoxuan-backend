package com.jf.controller;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.jf.common.ServerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

/**
 * AI 助教控制器
 * 严格遵循阿里巴巴通义千问官方文档重构 (SDK v2.12.0+)
 */
@RestController
@RequestMapping("/api/ai")
public class AiController {

    @PostMapping("/chat")
    public ServerResponse<String> chat(@RequestBody Map<String, String> payload, HttpSession session) {

//        // 1. 安全检查：验证用户是否登录
//        if (session.getAttribute("UserLogin") == null) {
//            return ServerResponse.createByErrorMessage("请先登录后再使用 AI 功能");
//        }

        // 2. 参数校验
        String question = payload.get("question");
        if (question == null || question.trim().isEmpty()) {
            return ServerResponse.createByErrorMessage("问题不能为空");
        }

        try {
            // 3. 初始化 Generation 实例
            Generation gen = new Generation();

            // 4. 构造对话消息 (官方推荐的多轮对话结构)
            // 4.1 系统级指令 (System Prompt)，用于设定 AI 的角色和行为
            Message systemMsg = Message.builder()
                    .role(Role.SYSTEM.getValue())
                    .content("你是秒选通选课系统中，为了更好帮助师生而诞生的一名人工智能，名字叫小选，你非常乐于助人，喜欢帮助同学老师解决问题.") // 你是一个乐于助人的助手
                    .build();

            // 4.2 用户的提问
            Message userMsg = Message.builder()
                    .role(Role.USER.getValue())
                    .content(question)
                    .build();

            // 5. 构造请求参数
            GenerationParam param = GenerationParam.builder()
                    // 5.1 【关键】从环境变量自动获取 API Key (官方推荐)
                    .apiKey("sk-0400f36c229e41db97178a0a4c259479")
                    // 5.2 指定模型 (qwen-plus 是一个功能更强的模型，也可换成 qwen-flash 等)
                    .model("qwen-flash")
                    // 5.3 传入完整的对话消息列表
                    .messages(Arrays.asList(systemMsg, userMsg))
                    // 5.4 设置返回格式为 message
                    .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                    .build();

            // 6. 调用模型并获取结果
            GenerationResult result = gen.call(param);

            // 7. 提取并返回 AI 的回答
            String aiResponse = result.getOutput().getChoices().get(0).getMessage().getContent();
            return ServerResponse.createBySuccess("回答成功", aiResponse);

        } catch (NoApiKeyException | InputRequiredException | ApiException e) {
            // 捕获官方 SDK 定义的核心异常
            System.err.println("调用 AI 服务时发生错误: " + e.getMessage());
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("调用 AI 服务时发生错误，请检查 API Key 配置或稍后再试");
        } catch (Exception e) {
            // 捕获其他未知异常
            System.err.println("发生未知系统错误: " + e.getMessage());
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("系统内部错误，请联系管理员");
        }
    }
}