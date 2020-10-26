package com.qianlei.zhifou.service;

import com.qianlei.zhifou.entity.Answer;
import com.qianlei.zhifou.vo.AnswerVo;

/** @author qianlei */
public interface IAnswerService {
  /**
   * 根据 id 获取回答信息
   * @param id 回答id
   * @return 回答信息
   */
  AnswerVo getAnswerById(int id);

  /**
   * 创建回答
   * @param answer 回答内容
   * @param token 创建者 token
   * @return 创建的回答信息
   */
  Answer createAnswer(Answer answer, String token);
}
