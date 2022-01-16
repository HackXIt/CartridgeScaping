package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.cartridge.CartridgeModel;

public class JoinDialogController implements DialogController{
    private CartridgeModel model;

    public JoinDialogController(CartridgeModel model) {
        this.model = model;
    }

    @Override
    public void consumeDialog() {
        // TODO Join game upon consuming dialog
    }
}
