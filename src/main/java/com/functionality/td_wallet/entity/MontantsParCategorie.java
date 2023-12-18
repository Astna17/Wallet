package com.functionality.td_wallet.entity;

import java.math.BigDecimal;

public class MontantsParCategorie {
        private BigDecimal restaurant;
        private BigDecimal salaire;

        public MontantsParCategorie(BigDecimal restaurant, BigDecimal salaire) {
            this.restaurant = restaurant;
            this.salaire = salaire;
        }

        // Getters and setters

        public BigDecimal getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(BigDecimal restaurant) {
            this.restaurant = restaurant;
        }

        public BigDecimal getSalaire() {
            return salaire;
        }

        public void setSalaire(BigDecimal salaire) {
            this.salaire = salaire;
        }


}
