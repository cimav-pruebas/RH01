package org.jboss.errai.ioc.client;

import javax.inject.Provider;
import org.jboss.errai.databinding.client.DataBinderProvider;
import org.jboss.errai.databinding.client.DataBindingModuleBootstrapper;
import org.jboss.errai.enterprise.client.cdi.CDIEventTypeLookup;
import org.jboss.errai.enterprise.client.cdi.EventProvider;
import org.jboss.errai.enterprise.client.cdi.InstanceProvider;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.ioc.client.api.ContextualTypeProvider;
import org.jboss.errai.ioc.client.api.builtin.CallerProvider;
import org.jboss.errai.ioc.client.api.builtin.DisposerProvider;
import org.jboss.errai.ioc.client.api.builtin.IOCBeanManagerProvider;
import org.jboss.errai.ioc.client.api.builtin.InitBallotProvider;
import org.jboss.errai.ioc.client.api.builtin.RootPanelProvider;
import org.jboss.errai.ioc.client.container.BeanProvider;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.SimpleCreationalContext;
import org.jboss.errai.ioc.client.container.SyncBeanManager;
import org.jboss.errai.ioc.client.lifecycle.api.Access;
import org.jboss.errai.ioc.client.lifecycle.api.Creation;
import org.jboss.errai.ioc.client.lifecycle.api.Destruction;
import org.jboss.errai.ioc.client.lifecycle.api.LifecycleEvent;
import org.jboss.errai.ioc.client.lifecycle.api.LifecycleListenerRegistrar;
import org.jboss.errai.ioc.client.lifecycle.api.StateChange;
import org.jboss.errai.ioc.client.lifecycle.impl.AccessImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.CreationImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.DestructionImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.LifecycleEventImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.LifecycleListenerRegistrarImpl;
import org.jboss.errai.ioc.client.lifecycle.impl.StateChangeImpl;
import org.jboss.errai.ioc.support.bus.client.BatchCallerProvider;
import org.jboss.errai.ioc.support.bus.client.MessageBusProvider;
import org.jboss.errai.ioc.support.bus.client.RequestDispatcherProvider;
import org.jboss.errai.ioc.support.bus.client.SenderProvider;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new DataBindingModuleBootstrapper().run();
  }

  private final SimpleInjectionContext injContext = new SimpleInjectionContext();
  private final SimpleCreationalContext context = injContext.getRootContext();
  private final BeanProvider<DataBinderProvider> inj2319_DataBinderProvider_creational = new BeanProvider<DataBinderProvider>() {
    public DataBinderProvider getInstance(final CreationalContext context) {
      final DataBinderProvider inj2312_DataBinderProvider = new DataBinderProvider();
      context.addBean(context.getBeanReference(DataBinderProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2312_DataBinderProvider);
      return inj2312_DataBinderProvider;
    }
  };
  private final DataBinderProvider inj2312_DataBinderProvider = inj2319_DataBinderProvider_creational.getInstance(context);
  private final BeanProvider<InstanceProvider> inj2320_InstanceProvider_creational = new BeanProvider<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      final InstanceProvider inj2318_InstanceProvider = new InstanceProvider();
      context.addBean(context.getBeanReference(InstanceProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2318_InstanceProvider);
      return inj2318_InstanceProvider;
    }
  };
  private final InstanceProvider inj2318_InstanceProvider = inj2320_InstanceProvider_creational.getInstance(context);
  private final BeanProvider<EventProvider> inj2321_EventProvider_creational = new BeanProvider<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      final EventProvider inj2302_EventProvider = new EventProvider();
      context.addBean(context.getBeanReference(EventProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2302_EventProvider);
      return inj2302_EventProvider;
    }
  };
  private final EventProvider inj2302_EventProvider = inj2321_EventProvider_creational.getInstance(context);
  private final BeanProvider<SenderProvider> inj2322_SenderProvider_creational = new BeanProvider<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      final SenderProvider inj2314_SenderProvider = new SenderProvider();
      context.addBean(context.getBeanReference(SenderProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2314_SenderProvider);
      return inj2314_SenderProvider;
    }
  };
  private final SenderProvider inj2314_SenderProvider = inj2322_SenderProvider_creational.getInstance(context);
  private final BeanProvider<IOCBeanManagerProvider> inj2323_IOCBeanManagerProvider_creational = new BeanProvider<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      final IOCBeanManagerProvider inj2310_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      context.addBean(context.getBeanReference(IOCBeanManagerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2310_IOCBeanManagerProvider);
      return inj2310_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj2310_IOCBeanManagerProvider = inj2323_IOCBeanManagerProvider_creational.getInstance(context);
  private final BeanProvider<CallerProvider> inj2324_CallerProvider_creational = new BeanProvider<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      final CallerProvider inj2304_CallerProvider = new CallerProvider();
      context.addBean(context.getBeanReference(CallerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2304_CallerProvider);
      return inj2304_CallerProvider;
    }
  };
  private final CallerProvider inj2304_CallerProvider = inj2324_CallerProvider_creational.getInstance(context);
  private final BeanProvider<StateChangeImpl> inj2326_StateChangeImpl_creational = new BeanProvider<StateChangeImpl>() {
    public StateChangeImpl getInstance(final CreationalContext context) {
      final StateChangeImpl inj2325_StateChangeImpl = new StateChangeImpl();
      context.addBean(context.getBeanReference(StateChangeImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2325_StateChangeImpl);
      return inj2325_StateChangeImpl;
    }
  };
  private final BeanProvider<MessageBusProvider> inj2327_MessageBusProvider_creational = new BeanProvider<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      final MessageBusProvider inj2296_MessageBusProvider = new MessageBusProvider();
      context.addBean(context.getBeanReference(MessageBusProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2296_MessageBusProvider);
      return inj2296_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj2296_MessageBusProvider = inj2327_MessageBusProvider_creational.getInstance(context);
  private final BeanProvider<InitBallotProvider> inj2328_InitBallotProvider_creational = new BeanProvider<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      final InitBallotProvider inj2300_InitBallotProvider = new InitBallotProvider();
      context.addBean(context.getBeanReference(InitBallotProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2300_InitBallotProvider);
      return inj2300_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj2300_InitBallotProvider = inj2328_InitBallotProvider_creational.getInstance(context);
  private final BeanProvider<RequestDispatcherProvider> inj2329_RequestDispatcherProvider_creational = new BeanProvider<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      final RequestDispatcherProvider inj2308_RequestDispatcherProvider = new RequestDispatcherProvider();
      context.addBean(context.getBeanReference(RequestDispatcherProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2308_RequestDispatcherProvider);
      return inj2308_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj2308_RequestDispatcherProvider = inj2329_RequestDispatcherProvider_creational.getInstance(context);
  private final BeanProvider<DestructionImpl> inj2331_DestructionImpl_creational = new BeanProvider<DestructionImpl>() {
    public DestructionImpl getInstance(final CreationalContext context) {
      final DestructionImpl inj2330_DestructionImpl = new DestructionImpl();
      context.addBean(context.getBeanReference(DestructionImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2330_DestructionImpl);
      return inj2330_DestructionImpl;
    }
  };
  private final BeanProvider<RootPanelProvider> inj2332_RootPanelProvider_creational = new BeanProvider<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      final RootPanelProvider inj2298_RootPanelProvider = new RootPanelProvider();
      context.addBean(context.getBeanReference(RootPanelProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2298_RootPanelProvider);
      return inj2298_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj2298_RootPanelProvider = inj2332_RootPanelProvider_creational.getInstance(context);
  private final BeanProvider<LifecycleListenerRegistrarImpl> inj2334_LifecycleListenerRegistrarImpl_creational = new BeanProvider<LifecycleListenerRegistrarImpl>() {
    public LifecycleListenerRegistrarImpl getInstance(final CreationalContext context) {
      final LifecycleListenerRegistrarImpl inj2333_LifecycleListenerRegistrarImpl = new LifecycleListenerRegistrarImpl();
      context.addBean(context.getBeanReference(LifecycleListenerRegistrarImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2333_LifecycleListenerRegistrarImpl);
      return inj2333_LifecycleListenerRegistrarImpl;
    }
  };
  private final LifecycleListenerRegistrarImpl inj2333_LifecycleListenerRegistrarImpl = inj2334_LifecycleListenerRegistrarImpl_creational.getInstance(context);
  private final BeanProvider<DisposerProvider> inj2335_DisposerProvider_creational = new BeanProvider<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      final DisposerProvider inj2306_DisposerProvider = new DisposerProvider();
      context.addBean(context.getBeanReference(DisposerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2306_DisposerProvider);
      _$1300398733__$652658075_beanManager(inj2306_DisposerProvider, inj2310_IOCBeanManagerProvider.get());
      return inj2306_DisposerProvider;
    }
  };
  private final DisposerProvider inj2306_DisposerProvider = inj2335_DisposerProvider_creational.getInstance(context);
  private final BeanProvider<BatchCallerProvider> inj2336_BatchCallerProvider_creational = new BeanProvider<BatchCallerProvider>() {
    public BatchCallerProvider getInstance(final CreationalContext context) {
      final BatchCallerProvider inj2316_BatchCallerProvider = new BatchCallerProvider();
      context.addBean(context.getBeanReference(BatchCallerProvider.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2316_BatchCallerProvider);
      return inj2316_BatchCallerProvider;
    }
  };
  private final BatchCallerProvider inj2316_BatchCallerProvider = inj2336_BatchCallerProvider_creational.getInstance(context);
  private final BeanProvider<CreationImpl> inj2338_CreationImpl_creational = new BeanProvider<CreationImpl>() {
    public CreationImpl getInstance(final CreationalContext context) {
      final CreationImpl inj2337_CreationImpl = new CreationImpl();
      context.addBean(context.getBeanReference(CreationImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2337_CreationImpl);
      return inj2337_CreationImpl;
    }
  };
  private final BeanProvider<AccessImpl> inj2340_AccessImpl_creational = new BeanProvider<AccessImpl>() {
    public AccessImpl getInstance(final CreationalContext context) {
      final AccessImpl inj2339_AccessImpl = new AccessImpl();
      context.addBean(context.getBeanReference(AccessImpl.class, QualifierUtil.DEFAULT_QUALIFIERS), inj2339_AccessImpl);
      return inj2339_AccessImpl;
    }
  };
  private void declareBeans_0() {
    injContext.addBean(DataBinderProvider.class, DataBinderProvider.class, inj2319_DataBinderProvider_creational, inj2312_DataBinderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, DataBinderProvider.class, inj2319_DataBinderProvider_creational, inj2312_DataBinderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj2320_InstanceProvider_creational, inj2318_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj2320_InstanceProvider_creational, inj2318_InstanceProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj2321_EventProvider_creational, inj2302_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj2321_EventProvider_creational, inj2302_EventProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj2322_SenderProvider_creational, inj2314_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj2322_SenderProvider_creational, inj2314_SenderProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj2323_IOCBeanManagerProvider_creational, inj2310_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj2323_IOCBeanManagerProvider_creational, inj2310_IOCBeanManagerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj2324_CallerProvider_creational, inj2304_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj2324_CallerProvider_creational, inj2304_CallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(StateChangeImpl.class, StateChangeImpl.class, inj2326_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(StateChange.class, StateChangeImpl.class, inj2326_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, StateChangeImpl.class, inj2326_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, StateChangeImpl.class, inj2326_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, StateChangeImpl.class, inj2326_StateChangeImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj2327_MessageBusProvider_creational, inj2296_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj2327_MessageBusProvider_creational, inj2296_MessageBusProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj2328_InitBallotProvider_creational, inj2300_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj2328_InitBallotProvider_creational, inj2300_InitBallotProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj2329_RequestDispatcherProvider_creational, inj2308_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj2329_RequestDispatcherProvider_creational, inj2308_RequestDispatcherProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(DestructionImpl.class, DestructionImpl.class, inj2331_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Destruction.class, DestructionImpl.class, inj2331_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, DestructionImpl.class, inj2331_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, DestructionImpl.class, inj2331_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, DestructionImpl.class, inj2331_DestructionImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj2332_RootPanelProvider_creational, inj2298_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj2332_RootPanelProvider_creational, inj2298_RootPanelProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleListenerRegistrarImpl.class, LifecycleListenerRegistrarImpl.class, inj2334_LifecycleListenerRegistrarImpl_creational, inj2333_LifecycleListenerRegistrarImpl, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(LifecycleListenerRegistrar.class, LifecycleListenerRegistrarImpl.class, inj2334_LifecycleListenerRegistrarImpl_creational, inj2333_LifecycleListenerRegistrarImpl, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj2335_DisposerProvider_creational, inj2306_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj2335_DisposerProvider_creational, inj2306_DisposerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(BatchCallerProvider.class, BatchCallerProvider.class, inj2336_BatchCallerProvider_creational, inj2316_BatchCallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Provider.class, BatchCallerProvider.class, inj2336_BatchCallerProvider_creational, inj2316_BatchCallerProvider, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(CreationImpl.class, CreationImpl.class, inj2338_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Creation.class, CreationImpl.class, inj2338_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, CreationImpl.class, inj2338_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, CreationImpl.class, inj2338_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, CreationImpl.class, inj2338_CreationImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(AccessImpl.class, AccessImpl.class, inj2340_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, true);
    injContext.addBean(Access.class, AccessImpl.class, inj2340_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, AccessImpl.class, inj2340_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEventImpl.class, AccessImpl.class, inj2340_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
    injContext.addBean(LifecycleEvent.class, AccessImpl.class, inj2340_AccessImpl_creational, null, QualifierUtil.DEFAULT_QUALIFIERS, null, false);
  }

  private native static void _$1300398733__$652658075_beanManager(DisposerProvider instance, SyncBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  // The main IOC bootstrap method.
  public SimpleInjectionContext bootstrapContainer() {
    declareBeans_0();
    return injContext;
  }
}