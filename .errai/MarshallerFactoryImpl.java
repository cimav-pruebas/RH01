package org.jboss.errai.marshalling.client.api;

import com.google.gwt.core.shared.GWT;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.enterprise.context.Dependent;
import org.jboss.errai.marshalling.client.marshallers.BigDecimalMarshaller;
import org.jboss.errai.marshalling.client.marshallers.BigIntegerMarshaller;
import org.jboss.errai.marshalling.client.marshallers.BooleanMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ByteMarshaller;
import org.jboss.errai.marshalling.client.marshallers.CharacterMarshaller;
import org.jboss.errai.marshalling.client.marshallers.DateMarshaller;
import org.jboss.errai.marshalling.client.marshallers.DoubleMarshaller;
import org.jboss.errai.marshalling.client.marshallers.FloatMarshaller;
import org.jboss.errai.marshalling.client.marshallers.IntegerMarshaller;
import org.jboss.errai.marshalling.client.marshallers.LinkedHashSetMarshaller;
import org.jboss.errai.marshalling.client.marshallers.LinkedListMarshaller;
import org.jboss.errai.marshalling.client.marshallers.LinkedMapMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ListMarshaller;
import org.jboss.errai.marshalling.client.marshallers.LongMarshaller;
import org.jboss.errai.marshalling.client.marshallers.MapMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ObjectMarshaller;
import org.jboss.errai.marshalling.client.marshallers.PriorityQueueMarshaller;
import org.jboss.errai.marshalling.client.marshallers.QualifyingMarshallerWrapper;
import org.jboss.errai.marshalling.client.marshallers.QueueMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SQLDateMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SetMarshaller;
import org.jboss.errai.marshalling.client.marshallers.ShortMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SortedMapMarshaller;
import org.jboss.errai.marshalling.client.marshallers.SortedSetMarshaller;
import org.jboss.errai.marshalling.client.marshallers.StringBufferMarshaller;
import org.jboss.errai.marshalling.client.marshallers.StringBuilderMarshaller;
import org.jboss.errai.marshalling.client.marshallers.StringMarshaller;
import org.jboss.errai.marshalling.client.marshallers.TimeMarshaller;
import org.jboss.errai.marshalling.client.marshallers.TimestampMarshaller;
@Dependent public class MarshallerFactoryImpl implements MarshallerFactory {
  private Map<String, Marshaller> marshallers = new HashMap<String, Marshaller>();
  abstract class Marshaller_for_java_lang_StackTraceElement implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_Throwable implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_ArithmeticException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_io_IOException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_IllegalArgumentException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_util_EmptyStackException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_ArrayStoreException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_ClassCastException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_util_ConcurrentModificationException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_IndexOutOfBoundsException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_NegativeArraySizeException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_NullPointerException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_StringIndexOutOfBoundsException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_UnsupportedOperationException implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_AssertionError implements GeneratedMarshaller { }
  abstract class Marshaller_for_java_lang_RuntimeException implements GeneratedMarshaller { }
  abstract class Marshaller_for_org_jboss_errai_common_client_PageRequest implements GeneratedMarshaller { }
  public MarshallerFactoryImpl() {
    final ObjectMarshaller java_lang_Object = new ObjectMarshaller();
    marshallers.put("java.lang.Object", java_lang_Object);
    final QueueMarshaller java_util_Queue = new QueueMarshaller();
    marshallers.put("java.util.Queue", java_util_Queue);
    marshallers.put("java.util.AbstractQueue", java_util_Queue);
    final QueueMarshaller java_util_AbstractQueue = new QueueMarshaller();
    marshallers.put("java.util.AbstractQueue", java_util_AbstractQueue);
    final ListMarshaller java_util_List = new ListMarshaller();
    marshallers.put("java.util.List", java_util_List);
    marshallers.put("java.util.AbstractList", java_util_List);
    marshallers.put("java.util.ArrayList", java_util_List);
    marshallers.put("java.util.Vector", java_util_List);
    marshallers.put("java.util.Stack", java_util_List);
    marshallers.put("java.util.Collections$SynchronizedRandomAccessList", java_util_List);
    marshallers.put("java.util.Collections$SingletonList", java_util_List);
    marshallers.put("java.util.Collections$SynchronizedList", java_util_List);
    marshallers.put("java.util.Collections$UnmodifiableRandomAccessList", java_util_List);
    marshallers.put("java.util.Collections$UnmodifiableList", java_util_List);
    marshallers.put("java.util.Collections$EmptyList", java_util_List);
    marshallers.put("java.util.Arrays$ArrayList", java_util_List);
    final ListMarshaller java_util_AbstractList = new ListMarshaller();
    marshallers.put("java.util.AbstractList", java_util_AbstractList);
    final ListMarshaller java_util_ArrayList = new ListMarshaller();
    marshallers.put("java.util.ArrayList", java_util_ArrayList);
    final ListMarshaller java_util_Vector = new ListMarshaller();
    marshallers.put("java.util.Vector", java_util_Vector);
    final ListMarshaller java_util_Stack = new ListMarshaller();
    marshallers.put("java.util.Stack", java_util_Stack);
    final LongMarshaller java_lang_Long = new LongMarshaller();
    marshallers.put("java.lang.Long", java_lang_Long);
    final SortedSetMarshaller java_util_SortedSet = new SortedSetMarshaller();
    marshallers.put("java.util.SortedSet", java_util_SortedSet);
    marshallers.put("java.util.TreeSet", java_util_SortedSet);
    marshallers.put("java.util.Collections$SynchronizedSortedSet", java_util_SortedSet);
    marshallers.put("java.util.Collections$UnmodifiableSortedSet", java_util_SortedSet);
    final SortedSetMarshaller java_util_TreeSet = new SortedSetMarshaller();
    marshallers.put("java.util.TreeSet", java_util_TreeSet);
    final TimeMarshaller java_sql_Time = new TimeMarshaller();
    marshallers.put("java.sql.Time", java_sql_Time);
    final CharacterMarshaller java_lang_Character = new CharacterMarshaller();
    marshallers.put("java.lang.Character", java_lang_Character);
    final LinkedListMarshaller java_util_LinkedList = new LinkedListMarshaller();
    marshallers.put("java.util.LinkedList", java_util_LinkedList);
    final SQLDateMarshaller java_sql_Date = new SQLDateMarshaller();
    marshallers.put("java.sql.Date", java_sql_Date);
    final BigIntegerMarshaller java_math_BigInteger = new BigIntegerMarshaller();
    marshallers.put("java.math.BigInteger", java_math_BigInteger);
    final DateMarshaller java_util_Date = new DateMarshaller();
    marshallers.put("java.util.Date", java_util_Date);
    final IntegerMarshaller java_lang_Integer = new IntegerMarshaller();
    marshallers.put("java.lang.Integer", java_lang_Integer);
    final BigDecimalMarshaller java_math_BigDecimal = new BigDecimalMarshaller();
    marshallers.put("java.math.BigDecimal", java_math_BigDecimal);
    final ByteMarshaller java_lang_Byte = new ByteMarshaller();
    marshallers.put("java.lang.Byte", java_lang_Byte);
    final StringBuilderMarshaller java_lang_StringBuilder = new StringBuilderMarshaller();
    marshallers.put("java.lang.StringBuilder", java_lang_StringBuilder);
    final BooleanMarshaller java_lang_Boolean = new BooleanMarshaller();
    marshallers.put("java.lang.Boolean", java_lang_Boolean);
    final DoubleMarshaller java_lang_Double = new DoubleMarshaller();
    marshallers.put("java.lang.Double", java_lang_Double);
    final LinkedHashSetMarshaller java_util_LinkedHashSet = new LinkedHashSetMarshaller();
    marshallers.put("java.util.LinkedHashSet", java_util_LinkedHashSet);
    final ShortMarshaller java_lang_Short = new ShortMarshaller();
    marshallers.put("java.lang.Short", java_lang_Short);
    final QualifyingMarshallerWrapper<Map> java_util_Map = new QualifyingMarshallerWrapper(new MapMarshaller(), MapMarshaller.class);
    marshallers.put("java.util.Map", java_util_Map);
    marshallers.put("java.util.AbstractMap", java_util_Map);
    marshallers.put("java.util.HashMap", java_util_Map);
    marshallers.put("java.util.Collections$UnmodifiableMap", java_util_Map);
    marshallers.put("java.util.Collections$SingletonMap", java_util_Map);
    marshallers.put("java.util.Collections$EmptyMap", java_util_Map);
    marshallers.put("java.util.Collections$SynchronizedMap", java_util_Map);
    final QualifyingMarshallerWrapper<AbstractMap> java_util_AbstractMap = new QualifyingMarshallerWrapper(new MapMarshaller(), MapMarshaller.class);
    marshallers.put("java.util.AbstractMap", java_util_AbstractMap);
    final QualifyingMarshallerWrapper<HashMap> java_util_HashMap = new QualifyingMarshallerWrapper(new MapMarshaller(), MapMarshaller.class);
    marshallers.put("java.util.HashMap", java_util_HashMap);
    final QualifyingMarshallerWrapper<SortedMap> java_util_SortedMap = new QualifyingMarshallerWrapper(new SortedMapMarshaller(), SortedMapMarshaller.class);
    marshallers.put("java.util.SortedMap", java_util_SortedMap);
    marshallers.put("java.util.TreeMap", java_util_SortedMap);
    marshallers.put("java.util.Collections$UnmodifiableSortedMap", java_util_SortedMap);
    marshallers.put("java.util.Collections$SynchronizedSortedMap", java_util_SortedMap);
    final QualifyingMarshallerWrapper<TreeMap> java_util_TreeMap = new QualifyingMarshallerWrapper(new SortedMapMarshaller(), SortedMapMarshaller.class);
    marshallers.put("java.util.TreeMap", java_util_TreeMap);
    final StringMarshaller java_lang_String = new StringMarshaller();
    marshallers.put("java.lang.String", java_lang_String);
    final StringBufferMarshaller java_lang_StringBuffer = new StringBufferMarshaller();
    marshallers.put("java.lang.StringBuffer", java_lang_StringBuffer);
    final FloatMarshaller java_lang_Float = new FloatMarshaller();
    marshallers.put("java.lang.Float", java_lang_Float);
    final SetMarshaller java_util_Set = new SetMarshaller();
    marshallers.put("java.util.Set", java_util_Set);
    marshallers.put("java.util.AbstractSet", java_util_Set);
    marshallers.put("java.util.HashSet", java_util_Set);
    marshallers.put("java.util.Collections$SynchronizedSet", java_util_Set);
    marshallers.put("java.util.Collections$SingletonSet", java_util_Set);
    marshallers.put("java.util.Collections$UnmodifiableSet", java_util_Set);
    marshallers.put("java.util.Collections$EmptySet", java_util_Set);
    final SetMarshaller java_util_AbstractSet = new SetMarshaller();
    marshallers.put("java.util.AbstractSet", java_util_AbstractSet);
    final SetMarshaller java_util_HashSet = new SetMarshaller();
    marshallers.put("java.util.HashSet", java_util_HashSet);
    final PriorityQueueMarshaller java_util_PriorityQueue = new PriorityQueueMarshaller();
    marshallers.put("java.util.PriorityQueue", java_util_PriorityQueue);
    final QualifyingMarshallerWrapper<LinkedHashMap> java_util_LinkedHashMap = new QualifyingMarshallerWrapper(new LinkedMapMarshaller(), LinkedMapMarshaller.class);
    marshallers.put("java.util.LinkedHashMap", java_util_LinkedHashMap);
    final TimestampMarshaller java_sql_Timestamp = new TimestampMarshaller();
    marshallers.put("java.sql.Timestamp", java_sql_Timestamp);
  }

  public Marshaller getMarshaller(String a0) {
    if (a0 == null) {
      return null;
    }
    if (marshallers.containsKey(a0)) {
      return marshallers.get(a0);
    }
    Marshaller m = null;
    if (a0.equals("java.lang.StackTraceElement")) {
      m = GWT.create(Marshaller_for_java_lang_StackTraceElement.class);
    } else if (a0.equals("java.lang.Throwable")) {
      m = GWT.create(Marshaller_for_java_lang_Throwable.class);
    } else if (a0.equals("java.lang.ArithmeticException")) {
      m = GWT.create(Marshaller_for_java_lang_ArithmeticException.class);
    } else if (a0.equals("java.io.IOException")) {
      m = GWT.create(Marshaller_for_java_io_IOException.class);
    } else if (a0.equals("java.lang.IllegalArgumentException")) {
      m = GWT.create(Marshaller_for_java_lang_IllegalArgumentException.class);
    } else if (a0.equals("java.util.EmptyStackException")) {
      m = GWT.create(Marshaller_for_java_util_EmptyStackException.class);
    } else if (a0.equals("java.lang.ArrayStoreException")) {
      m = GWT.create(Marshaller_for_java_lang_ArrayStoreException.class);
    } else if (a0.equals("java.lang.ClassCastException")) {
      m = GWT.create(Marshaller_for_java_lang_ClassCastException.class);
    } else if (a0.equals("java.util.ConcurrentModificationException")) {
      m = GWT.create(Marshaller_for_java_util_ConcurrentModificationException.class);
    } else if (a0.equals("java.lang.IndexOutOfBoundsException")) {
      m = GWT.create(Marshaller_for_java_lang_IndexOutOfBoundsException.class);
    } else if (a0.equals("java.lang.NegativeArraySizeException")) {
      m = GWT.create(Marshaller_for_java_lang_NegativeArraySizeException.class);
    } else if (a0.equals("java.lang.NullPointerException")) {
      m = GWT.create(Marshaller_for_java_lang_NullPointerException.class);
    } else if (a0.equals("java.lang.StringIndexOutOfBoundsException")) {
      m = GWT.create(Marshaller_for_java_lang_StringIndexOutOfBoundsException.class);
    } else if (a0.equals("java.lang.UnsupportedOperationException")) {
      m = GWT.create(Marshaller_for_java_lang_UnsupportedOperationException.class);
    } else if (a0.equals("java.lang.AssertionError")) {
      m = GWT.create(Marshaller_for_java_lang_AssertionError.class);
    } else if (a0.equals("java.lang.RuntimeException")) {
      m = GWT.create(Marshaller_for_java_lang_RuntimeException.class);
    } else if (a0.equals("org.jboss.errai.common.client.PageRequest")) {
      m = GWT.create(Marshaller_for_org_jboss_errai_common_client_PageRequest.class);
    }
    if (m != null) {
      marshallers.put(a0, m);
    }
    return m;
  }

  public void registerMarshaller(String a0, Marshaller a1) {
    marshallers.put(a0, a1);
  }
}