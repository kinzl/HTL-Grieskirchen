/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos2.util;

import java.io.File;
/**
 *
 * @author Torsten Welsch
 */
public class ClientUtil {

    public static void resetFiles() {
        File csvFile = new File("tips.csv");
        csvFile.delete();
    }
}
