/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import java.sql.Timestamp;

/**
 * A simple value type to support the concept of "Period".
 * <p>Note, like Address, this class is not an entiy.
 *
 * @author richard.qin
 */

@Embeddable
public class Period {
    public static final Timestamp EPOCH = new Timestamp(0);

    @Column(name = "FROM_DATE")
    private Timestamp from;

    @Column(name = "TO_DATE")
    private Timestamp to;

    public Period() {
    }

    public Period(Timestamp from, Timestamp to) {
        this.from = from;
        this.to = to;
    }

    /**
     * @return the from
     */
    public Timestamp getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Timestamp from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public Timestamp getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Timestamp to) {
        this.to = to;
    }
}
