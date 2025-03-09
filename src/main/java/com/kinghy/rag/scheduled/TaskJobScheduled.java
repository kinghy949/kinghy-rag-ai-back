package com.kinghy.rag.scheduled;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.kinghy.rag.entity.LogInfo;
import com.kinghy.rag.entity.WordFrequency;
import com.kinghy.rag.service.LogInfoService;
import com.kinghy.rag.service.WordFrequencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Title: TaskJobScheduled
 * @Author KingHY
 * @Package com.kinghy.rag.scheduled
 * @Date 2025/3/6 15:13
 * @description: 分词器定时任务
 */

@Component("taskJob")
@Slf4j
public class TaskJobScheduled {

    @Autowired
    private LogInfoService logInfoService;

    @Autowired
    private WordFrequencyService wordFrequencyService;
    @Scheduled(cron = "0 0 * * * ?")
    public void taskJob() {
        log.info("分词器定时任务开始执行");
        List<WordFrequency> wordFrequencies = wordFrequencyService.list();
        Map<String, List<WordFrequency>> collectMap = wordFrequencies.stream().collect(Collectors.groupingBy(WordFrequency::getWord));
        StringBuilder text = new StringBuilder();
        List<LogInfo> list = logInfoService.list((Wrapper<LogInfo>) null);
        for (LogInfo logInfo : list){
            text.append(logInfo.getRequestParams());
        }
        String result = text.toString();
        try (StringReader reader = new StringReader(result)) {
            IKSegmenter segment = new IKSegmenter(reader, true);
            Lexeme lexeme;
            List<WordFrequency> wordFrequencyList = new ArrayList<>();
            List<WordFrequency> updateList = new ArrayList<>();
            while ((lexeme = segment.next()) != null) {
                if (lexeme.getLength() <= 1){
                    continue;
                }
                if (lexeme.getLength() >=10){
                    continue;
                }
                if (!collectMap.containsKey(lexeme.getLexemeText())){
                    WordFrequency wordFrequency = new WordFrequency();
                    wordFrequency.setWord(lexeme.getLexemeText());
                    wordFrequency.setCountNum(1);
                    wordFrequency.setBusinessType("log");
                    wordFrequency.setCreateTime(new Date());
                    wordFrequency.setUpdateTime(new Date());
                    wordFrequencyList.add(wordFrequency);
                }else if (collectMap.containsKey(lexeme.getLexemeText())){
                    WordFrequency wordFrequency = collectMap.get(lexeme.getLexemeText()).get(0);
                    wordFrequency.setCountNum(wordFrequency.getCountNum()+1);
                    wordFrequency.setUpdateTime(new Date());
                    updateList.add(wordFrequency);
                }
            }
            wordFrequencyService.saveBatch(wordFrequencyList);
            wordFrequencyService.saveOrUpdateBatch(updateList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
