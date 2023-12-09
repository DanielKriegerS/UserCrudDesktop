package com.danielks.UserCrud;

import java.awt.*;

public class Defins extends GridBagConstraints {

    public Defins(int linha, int coluna, boolean hGrab, boolean vGrab, int posicao) {
        gridy = linha; // linha
        gridx = coluna; // coluna

        if (hGrab && vGrab) {
            fill = BOTH;
            weighty = 1.0; // linha
            weightx = 1.0; // coluna
        } else if (hGrab) {
            fill = HORIZONTAL;
            weightx = 1.0; // coluna
        } else if (vGrab) {
            fill = VERTICAL;
            weighty = 1.0; // linha
        }
        anchor = posicao;
        // espacamento
        insets = new Insets(0, 1, 1, 1);
    }

    public Defins(int linha, int coluna, boolean hGrab) {
        this(linha, coluna, hGrab,false);
    }
    public Defins(int linha, int coluna, boolean hGrab, boolean vGrab) {
        this(linha, coluna, hGrab, vGrab, LINE_START);
    }

    public Defins(int linha, int coluna) {
        this(linha, coluna, false, false);
    }

    public Defins(int linha, int coluna, int posicao) {
        this(linha, coluna, false, false, posicao);
    }

    public Defins setPadding(int value) {
        insets = new Insets(value, value, value, value);
        return this;
    }

    public Defins colSpan(int value) {
        gridwidth = value;
        return this;
    }

    public Defins rowSpan(int value) {
        gridheight = value;
        return this;
    }}
