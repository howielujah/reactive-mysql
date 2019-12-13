package com.howielujah.reactivemysql.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
  @Id
  @Column("id")
  private Integer id;

  @Column("name")
  private String name;

  @Column("gender")
  private String gender;

  @Column("position")
  private String position;

  @Column("create_time")
  private LocalDateTime createTime;
}
