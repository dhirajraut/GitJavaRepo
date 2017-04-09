/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 */
package com.intertek.phoenix.entity;

import javax.persistence.*;

/**
 * @author richard.qin
 * @author eric.nguyen
 */
@Entity
@Table(name = "PHX_ATTACHMENT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CARRIER_TYPE",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("GENERAL")
public class Attachment {
    @Id
    @SequenceGenerator(name = "PHX_Attachment_seq", sequenceName = "PHX_ATTACHMENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_Attachment_seq")
    @Column(name = "ID")
    private Long id;
    @Column(name = "FILE_NAME")
    private String filename;
    @Column(name = "DESCRIPTION")
    private String description;

    public Attachment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
