package org.apache.directmemory.serialization;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;

public final class StandardSerializer
    implements Serializer
{

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> byte[] serialize( T obj )
        throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( obj );
        oos.flush();
        oos.close();
        return baos.toByteArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T deserialize( byte[] source, final Class<T> clazz )
        throws IOException, ClassNotFoundException
    {
        ByteArrayInputStream bis = new ByteArrayInputStream( source );
        ObjectInputStream ois = new ObjectInputStream( bis )
        {

            @Override
            protected Class<?> resolveClass( ObjectStreamClass objectStreamClass )
                throws IOException, ClassNotFoundException
            {
                ClassLoader classLoader = clazz.getClassLoader();
                return classLoader != null
                    ? classLoader.loadClass( objectStreamClass.getName() )
                    : Class.forName( objectStreamClass.getName() );
            }

        };
        T obj = clazz.cast( ois.readObject() );
        ois.close();
        return obj;
    }

}
