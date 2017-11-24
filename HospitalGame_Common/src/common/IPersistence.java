/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.lang.reflect.Type;

/**
 *
 * @author fsr19
 */
public interface IPersistence {
    boolean save(Object object);
    Object load(Type type);
}
