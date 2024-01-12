package examplemod;

import legend.core.IoHelper;
import legend.game.inventory.screens.controls.NumberSpinner;
import legend.game.saves.ConfigEntry;
import legend.game.saves.ConfigStorageLocation;

public class DXPModifierConfigEntry extends ConfigEntry<Float> {
    public DXPModifierConfigEntry() {
        super(1.0f, ConfigStorageLocation.CAMPAIGN, DXPModifierConfigEntry::serializer, DXPModifierConfigEntry::deserializer);
        this.setEditControl((number, gameState) -> {
            final NumberSpinner<Float> spinner = NumberSpinner.floatSpinner(number, 0.25f, 0.25f, 10.00f);
            spinner.onChange(val -> gameState.setConfig(this, val));
            return spinner;

        });
    }

    private static byte[] serializer(final float val) {
        return new byte[]{(byte) (Math.round(val * 4.0f))};

    }

    private static Float deserializer(final byte[] data) {
        if (data.length == 1) {
            return IoHelper.readUByte(data, 0) / 4.0f;

        }
        return 0.25f;

    }

}
