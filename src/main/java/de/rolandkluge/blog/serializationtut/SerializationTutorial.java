package de.rolandkluge.blog.serializationtut;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.lang.SerializationUtils;

public class SerializationTutorial
{
    public static void main(final String[] args)
    {
        final String object;
        final File targetFile = new File("./target/serializedObject.ser");
        try {
            // File is not serialized, so write it to file
            if (!targetFile.exists()) {
                System.out.println("No serialized file present.");
                object = "I am very expensive to create, so serialize me!";

                final BufferedOutputStream outStream = new BufferedOutputStream(
                        new FileOutputStream(targetFile));
                SerializationUtils.serialize(object, outStream);
            }
            // The target file already exists, so we may assume that it contains the serialized
            // object
            else {
                System.out.println("Loading serialized object from file.");
                final BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(
                        targetFile));
                object = (String) SerializationUtils.deserialize(inStream);

            }

            // Use the object...
            System.out.println(object.toString());
        }
        catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
