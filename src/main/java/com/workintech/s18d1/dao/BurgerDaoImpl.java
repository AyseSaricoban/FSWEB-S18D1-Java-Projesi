package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BurgerDaoImpl implements BurgerDao{

    private final EntityManager entityManager;

    @Autowired
    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findById(long id) {
        return entityManager.find(Burger.class,id);
    }

    @Override
    public List<Burger> findAll() {
        return entityManager.createQuery("SELECT b FROM Burger b", Burger.class).getResultList();    }

    @Override
    public List<Burger> findByPrice(double price) {
        return entityManager.createQuery("SELECT b FROM Burger b WHERE b.price > :price ORDER BY b.price DESC", Burger.class)
                .setParameter("price", price)
                .getResultList();    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        return entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType=breadType ORDER BY b.name ASC ", Burger.class)
        .setParameter("breadType",breadType)
        .getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        return entityManager.createQuery("SELECT b FROM Burger b WHERE b.contents LIKE CONCAT('%', :content, '%')", Burger.class)
                .setParameter("content", content)
                .getResultList();    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(long id) {
        Burger found=findById(id);
        entityManager.remove(found);
        return found;
    }
}
