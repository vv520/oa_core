package com.htwy.oa.entity.project;

import com.htwy.oa.entity.user.User;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Vv
 * @version 1.0
 * @description: 项目款实体类
 * @date 2022/2/21 0021 16:05
 */
@Data
@Entity
@Table(name = "aoa_project_funds")
public class ProjectFunds {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_funds_id")
    private Long projectFundsId;

    /**
     * 技术人员id
     */
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
   /* @Column(name = "user_id")
    private Long userId;*/

/*    *//**
     * 技术人员
     *//*
    @Column(name = "user_real_name")
    private String userRealName;*/

    /**
     * 合同名称
     */
    @Column(name = "contract_name")
    private String contractName;

    /**
     * 单价
     */
    @Column(name = "price")
    private double price;

    /**
     * 人天月份
     */
    @Column(name = "month")
    private String month;

    /**
     * 人天
     */
    @Column(name = "work_days")
    private Long workDays;

    /**
     * 项目款
     */
    @Column(name = "project_amount")
    private double projectAmount;

    /**
     * 开票金额
     */
    @Column(name = "invoice_amount")
    private double invoiceAmount;

    /**
     * 已收款金额
     */
    @Column(name = "received_amount")
    private double receivedAmount;
}
