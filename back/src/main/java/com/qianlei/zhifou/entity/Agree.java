package com.qianlei.zhifou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/** @author qianlei */
@Table(name = "zhifou_agree")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agree {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "answer_id")
  private Integer answerId;
}
