package com.intertek.phoenix.entity;

import org.hibernate.annotations.Index;

import javax.persistence.*;

/**
 * @author lily.sun
 */
@Entity
@Table(name = "PHX_INSTRUCTION")
public class Instruction {

    @Id
    @SequenceGenerator(name = "PHX_Instruction_seq_gen", sequenceName = "PHX_INSTRUCTION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_Instruction_seq_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "INSTRUCTION_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private InstructionType instructionType;

    @Column(name = "INSTRUCTION", length = 3048)
    private String instruction;

    @Column(name = "JOB_NUMBER", updatable = false, insertable = false)
    private String jobNumber;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_NUMBER")
    @Index(name="PHX_INSTRUCTION_IDX_JOB_ORDER")
    private CEJobOrder jobOrder;

    public Instruction() {
    }

    public Instruction(InstructionType type) {
        instructionType = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public CEJobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(CEJobOrder jobOrder) {
        this.jobNumber = jobOrder.getJobNumber();
        this.jobOrder = jobOrder;
    }
}


