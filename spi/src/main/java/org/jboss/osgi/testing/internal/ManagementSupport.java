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
package org.jboss.osgi.testing.internal;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;

import org.jboss.osgi.jmx.BundleStateMBeanExt;
import org.jboss.osgi.jmx.FrameworkMBeanExt;
import org.jboss.osgi.jmx.ObjectNameFactory;
import org.jboss.osgi.jmx.PackageStateMBeanExt;
import org.jboss.osgi.jmx.ServiceStateMBeanExt;
import org.osgi.jmx.framework.BundleStateMBean;
import org.osgi.jmx.framework.FrameworkMBean;
import org.osgi.jmx.framework.PackageStateMBean;
import org.osgi.jmx.framework.ServiceStateMBean;

/**
 * A helper for bundle management.
 *
 * @author Thomas.Diesler@jboss.org
 * @since 25-Sep-2008
 */
public class ManagementSupport
{
   private MBeanServerConnection mbeanServer;

   public ManagementSupport(MBeanServerConnection mbeanServer)
   {
      if (mbeanServer == null)
         throw new IllegalArgumentException("Null mbeanServer");
      this.mbeanServer = mbeanServer;
   }

   public <T> T getMBeanProxy(ObjectName name, Class<T> interf)
   {
      return (T)MBeanServerInvocationHandler.newProxyInstance(mbeanServer, name, interf, false);
   }

   public MBeanServerConnection getMBeanServer()
   {
      return mbeanServer;
   }

   public FrameworkMBean getFrameworkMBean() throws IOException
   {
      FrameworkMBean frameworkState = null;
      ObjectName objectName = ObjectNameFactory.create(FrameworkMBeanExt.OBJECTNAME);
      if (isRegisteredWithTimeout(objectName))
      {
         frameworkState = getMBeanProxy(objectName, FrameworkMBeanExt.class);
         return frameworkState;
      }
      objectName = ObjectNameFactory.create(FrameworkMBean.OBJECTNAME);
      if (isRegisteredWithTimeout(objectName))
      {
         frameworkState = getMBeanProxy(objectName, FrameworkMBean.class);
         return frameworkState;
      }
      return frameworkState;
   }

   public BundleStateMBean getBundleStateMBean() throws IOException
   {
      BundleStateMBean bundleState = null;
      ObjectName objectName = ObjectNameFactory.create(BundleStateMBeanExt.OBJECTNAME);
      if (isRegisteredWithTimeout(objectName))
      {
         bundleState = getMBeanProxy(objectName, BundleStateMBeanExt.class);
         return bundleState;
      }
      objectName = ObjectNameFactory.create(BundleStateMBean.OBJECTNAME);
      if (isRegisteredWithTimeout(objectName))
      {
         bundleState = getMBeanProxy(objectName, BundleStateMBean.class);
         return bundleState;
      }
      return bundleState;
   }

   public PackageStateMBean getPackageStateMBean() throws IOException
   {
      PackageStateMBean packageState = null;
      ObjectName objectName = ObjectNameFactory.create(PackageStateMBeanExt.OBJECTNAME);
      if (isRegisteredWithTimeout(objectName))
      {
         packageState = getMBeanProxy(objectName, PackageStateMBeanExt.class);
         return packageState;
      }
      objectName = ObjectNameFactory.create(PackageStateMBean.OBJECTNAME);
      if (isRegisteredWithTimeout(objectName))
      {
         packageState = getMBeanProxy(objectName, PackageStateMBean.class);
         return packageState;
      }
      return packageState;
   }

   public ServiceStateMBean getServiceStateMBean() throws IOException
   {
      ServiceStateMBean serviceState = null;
      ObjectName objectName = ObjectNameFactory.create(ServiceStateMBeanExt.OBJECTNAME);
      if (isRegisteredWithTimeout(objectName))
      {
         serviceState = getMBeanProxy(objectName, ServiceStateMBeanExt.class);
         return serviceState;
      }
      objectName = ObjectNameFactory.create(ServiceStateMBean.OBJECTNAME);
      if (isRegisteredWithTimeout(objectName))
      {
         serviceState = getMBeanProxy(objectName, ServiceStateMBean.class);
         return serviceState;
      }
      return serviceState;
   }

   private boolean isRegisteredWithTimeout(ObjectName objectName) throws IOException
   {
      int timeout = 10000;
      boolean registered = mbeanServer.isRegistered(objectName);
      while(registered == false && timeout > 0)
      {
         try
         {
            Thread.sleep(100);
         }
         catch (InterruptedException ex)
         {
            // ignore
         }
         registered = mbeanServer.isRegistered(objectName);
         timeout -= 100;
      }
      return registered;
   }
}