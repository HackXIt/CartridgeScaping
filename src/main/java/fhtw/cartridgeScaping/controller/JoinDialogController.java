package fhtw.cartridgeScaping.controller;

import fhtw.cartridgeScaping.cartridge.CartridgeController;
import fhtw.cartridgeScaping.cartridge.CartridgeModel;

public class JoinDialogController extends CartridgeController implements DialogController{

    public JoinDialogController() {
        super();
    }

    @Override
    public void consumeDialog() {
        // TODO Join game upon consuming dialog
    }


    @Override
    public void onLoad() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void onSave() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void onApply() {
        throw new UnsupportedOperationException();
    }
}
