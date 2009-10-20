/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.osgi.spi.framework;

//$Id$

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A generic Bundle wrapper that delegates all method calls to the underlying 
 * Bundle implementation.
 * 
 * @author thomas.diesler@jboss.com
 * @since 16-Oct-2009
 */
public class BundleWrapper implements Bundle
{
   // Provide logging
   final Logger log = LoggerFactory.getLogger(BundleWrapper.class);

   protected Bundle bundle;

   public BundleWrapper(Bundle bundle)
   {
      if (bundle == null)
         throw new IllegalArgumentException("Null bundle");
      this.bundle = bundle;
   }

   @SuppressWarnings("unchecked")
   public Enumeration findEntries(String path, String filePattern, boolean recurse)
   {
      return bundle.findEntries(path, filePattern, recurse);
   }

   public BundleContext getBundleContext()
   {
      return bundle.getBundleContext();
   }

   public long getBundleId()
   {
      return bundle.getBundleId();
   }

   public URL getEntry(String path)
   {
      return bundle.getEntry(path);
   }

   @SuppressWarnings("unchecked")
   public Enumeration getEntryPaths(String path)
   {
      return bundle.getEntryPaths(path);
   }

   @SuppressWarnings("unchecked")
   public Dictionary getHeaders()
   {
      return bundle.getHeaders();
   }

   @SuppressWarnings("unchecked")
   public Dictionary getHeaders(String locale)
   {
      return bundle.getHeaders(locale);
   }

   public long getLastModified()
   {
      return bundle.getLastModified();
   }

   public String getLocation()
   {
      return bundle.getLocation();
   }

   public ServiceReference[] getRegisteredServices()
   {
      return bundle.getRegisteredServices();
   }

   public URL getResource(String name)
   {
      return bundle.getResource(name);
   }

   @SuppressWarnings("unchecked")
   public Enumeration getResources(String name) throws IOException
   {
      return bundle.getResources(name);
   }

   public ServiceReference[] getServicesInUse()
   {
      return bundle.getServicesInUse();
   }

   @SuppressWarnings("unchecked")
   public Map getSignerCertificates(int signersType)
   {
      return bundle.getSignerCertificates(signersType);
   }

   public int getState()
   {
      return bundle.getState();
   }

   public String getSymbolicName()
   {
      return bundle.getSymbolicName();
   }

   public Version getVersion()
   {
      return bundle.getVersion();
   }

   public boolean hasPermission(Object permission)
   {
      return bundle.hasPermission(permission);
   }

   @SuppressWarnings("unchecked")
   public Class loadClass(String name) throws ClassNotFoundException
   {
      return bundle.loadClass(name);
   }

   public void start() throws BundleException
   {
      bundle.start();
   }

   public void start(int options) throws BundleException
   {
      bundle.start(options);
   }

   public void stop() throws BundleException
   {
      bundle.stop();
   }

   public void stop(int options) throws BundleException
   {
      bundle.stop(options);
   }

   public void uninstall() throws BundleException
   {
      bundle.uninstall();
   }

   public void update() throws BundleException
   {
      bundle.update();
   }

   public void update(InputStream input) throws BundleException
   {
      bundle.update(input);
   }
}