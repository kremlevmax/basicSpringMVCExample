package org.example.config.controllers;

import org.example.config.dao.PetDAO;
import org.example.config.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pets")
public class PetsController {
    public PetDAO petDAO;

    @Autowired
    public PetsController(PetDAO petDAO) {
        this.petDAO = petDAO;
    }

    @GetMapping()
    public String showListOfPets(Model model) {
        model.addAttribute("pets", petDAO.index());

        return "pets/index";
    }

    @GetMapping("/{id}")
    public String showOnePet(@PathVariable("id") int id, Model model){
        model.addAttribute("pet", petDAO.showById(id));
        return "pets/show";
    }

    @GetMapping("/new")
    public String addPetForm(Model model, Pet pet) {
        model.addAttribute("pet", pet);
        return "pets/new";
    }

    @PostMapping()
    public String postPetObject(@ModelAttribute("pet") Pet pet) {
        petDAO.save(pet);
        return "redirect:/pets";
    }
}
