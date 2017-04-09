/**  
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.ProductGroup;

/**
 * 
 * @author eric.nguyen
 */
@Entity
@Table(name = "PHX_PRODUCTGROUP_PRODUCT")
public class ProductGroupProduct {
    @Id
    @SequenceGenerator(name = "PHX_pdg_Product_seq", sequenceName = "PHX_pdg_PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_pdg_Product_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_ID", updatable = false, insertable = false)
    private Long productId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "PRODUCT_ID")
    @Index(name = "PHX_PDG_PROD_IDX_PROD")
    private Product product;

    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "PRODUCT_GROUP_SET", referencedColumnName = "PRODUCT_GROUP_SET"),
                   @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID"), @JoinColumn(name = "BEGIN_DATE", referencedColumnName = "BEGIN_DATE") })
    private ProductGroup productGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }
}
