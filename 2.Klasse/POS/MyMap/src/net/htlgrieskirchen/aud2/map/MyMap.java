/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.aud2.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Torsten Welsch
 */
public class MyMap {

    private int size = 0;
    private Entry root;
    private Set<Entry> entrySet = new TreeSet<>();

    public MyMap() {
    }


    public String put(String key, String value) {

        Entry temp = root;
        Entry time = root;
        if (key == null) {
            return null;
        } else if (root == null) {
            root = new Entry(key, value, null);
            entrySet.add(new Entry(key, value, root));
        } else {

            while (time != null) {

                if (key.compareTo(time.key) > 0) {
                    temp = time;
                    time = time.right;
                    entrySet.add(new Entry(key, value, temp));
                } else if (key.compareTo(time.key) < 0) {
                    temp = time;
                    time = time.left;
                    entrySet.add(new Entry(key, value, temp));
                } else {
                    String val = time.value;
                    time.value = value;
                    return val;
                }
            }

            time = new Entry(key, value, temp);
            entrySet.add(time);

            if (key.compareTo(temp.key) > 0) {
                temp.right = time;
            } else if (key.compareTo(temp.key) < 0) {
                temp.left = time;
            }

            entrySet.add(time);

        }
        size++;
        return null;
    }
    
    public String get(String key) {

        if (key == null) {
            throw new NullPointerException();
        } else {

            for (Entry entry : entrySet) {
                if(entry.key.compareTo(key) < 0){
                    entry = entry.right;
                } else if(entry.key.compareTo(key) > 0){
                    entry = entry.left;
                } else if(entry.key.compareTo(key) == 0){
                    return entry.value;
                }
            }
            return null;
        }
    }

    public String remove(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        while (true) {
            if (root.key.compareTo(key) == 0) {
                root.value = null;
                entrySet.remove(root.value);
                size--;
            } else if (root.key.compareTo(key) < 0) {
                if (root.left == null) {
                    break;
                } else {
                    root = root.left;
                }
            } else if (root.key.compareTo(key) > 0) {
                if (root.right == null) {
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        return null;
    }

    public Set<String> keySet() {

        Set<String> keySet = new TreeSet();

        for (Entry entry : entrySet) {
            keySet.add(entry.key);
        }

        return keySet;
    }

    public Collection<String> values() {

        List<Entry> array = new ArrayList<>(entrySet);
        
        Collections.sort(array, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.compareTo(o2);
            }
        });
        
        List<String> values = new ArrayList<>();
        
        for (Entry entry : array) {
            values.add(entry.value);
        }
        
        return values;
    }

    public Set<Entry> entrySet() {

        return entrySet;
    }

    public boolean containsKey(String key) {

        if (key == null) {
            throw new NullPointerException();
        }

        if (!entrySet.isEmpty()) {
            for (Entry entry : entrySet) {
                if (entry.key.compareTo(key) < 0) {
                    entry = entry.right;
                } else if (entry.key.compareTo(key) > 0) {
                    entry = entry.left;
                } else if (entry.key.compareTo(key) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(String value) {

        if (value == null) {
            throw new NullPointerException();
        }

        if (!entrySet.isEmpty()) {
            for (Entry entry : entrySet) {
                if (entry.value.compareTo(value) < 0) {
                    entry = entry.right;
                } else if (entry.value.compareTo(value) > 0) {
                    entry = entry.left;
                } else if (entry.value.compareTo(value) == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {

        if (size == 0) {
            return true;
        }
        return false;

    }

    static class Entry implements Comparable<Entry>{

        String key;
        String value;

        Entry parent;
        Entry left;
        Entry right;

        Entry(String key, String value, Entry parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public int compareTo(Entry o) {
            return key.compareTo(o.key);
        }

    }
    
}
