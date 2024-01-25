package com.realEstate.realEstate.service.ChatGpt;

import com.realEstate.realEstate.model.dto.ChatGpt.ChatCompletionDto;
import com.realEstate.realEstate.model.dto.ChatGpt.CompletionDto;
import com.realEstate.realEstate.model.dto.ChatGpt.CompletionRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ChatGPTService {

    List<Map<String, Object>> modelList();

    Map<String, Object> isValidModel(String modelName);

    Map<String, Object> legacyPrompt(CompletionDto completionDto);

    Map<String, Object> prompt(ChatCompletionDto chatCompletionDto);
}

