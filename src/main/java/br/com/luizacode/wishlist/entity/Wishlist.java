package br.com.luizacode.wishlist.entity;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "wishlist")
public class Wishlist implements Serializable {

    private static final Long serialVersionUID = 1L;


}
