package com.example;

import com.example.controller.HotelController;
import com.example.view.TelaInicialView;

public class Main {
    public static void main(String[] args) {
    HotelController controller = new HotelController();
    new TelaInicialView(controller);
}

}
