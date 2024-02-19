package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/burger")
public class BurgerController {
    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }
    @PostMapping
    public Burger save (@RequestBody Burger burger){
        return burgerDao.save(burger);
    }

    @GetMapping
    public List<Burger> findAll() {
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger getBurgerById(@PathVariable long id) {
        return burgerDao.findById(id);
    }
    @PutMapping
    public Burger update(@RequestBody Burger burger){
        return burgerDao.update(burger);
    }
    @DeleteMapping("/{id}")
    public Burger remove (@PathVariable long id){
        return burgerDao.remove(id);
    }
    @GetMapping("/findByBreadType")
    public List<Burger> findBurgersByBreadType(@RequestParam String breadType) {
        return burgerDao.findByBreadType(BreadType.valueOf(breadType));
    }
    @GetMapping("/findByPrice")
    public List<Burger> findBurgersByPrice(@RequestParam double price) {
        return burgerDao.findByPrice(price);
    }
    @GetMapping("/findByContent")
    public List<Burger> findBurgersByContent(@RequestParam String content) {
        return burgerDao.findByContent(content);
    }
}
