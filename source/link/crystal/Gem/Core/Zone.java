//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Gem.Core;


import java.lang.RuntimeException;
import java.lang.Thread;
import link.crystal.Gem.Core.Gem_Object;
import link.crystal.Gem.Core.Gem_StringBuilder;
import link.crystal.Gem.Format.AdornmentSegmentFormatter;
import link.crystal.Gem.Format.Map__String__ArgumentSegmentFormatter_Inspection;
import link.crystal.Gem.Format.NormalSegmentFormatter;
import link.crystal.Gem.Format.ParseFormat;
import link.crystal.Gem.Format.PortraySegmentFormatter;
import link.crystal.Gem.Format.StringSegmentFormatter;
import link.crystal.Gem.Inspection.Comparable_Inspection;
import link.crystal.Gem.Inspection.Gem_Reference_Inspection;
import link.crystal.Gem.Inspection.Inspection;
import link.crystal.Gem.Interface.Gem_ComparableReference_Interface;
import link.crystal.Gem.Interface.Inspectable;
import link.crystal.Gem.Interface.Storehouse_String__Interface;
import link.crystal.Gem.Support.Storehouse_AdornmentSegmentFormatter;
import link.crystal.Gem.Support.Storehouse_MessageFormattable;
import link.crystal.Gem.Support.Storehouse_NormalSegmentFormatter;
import link.crystal.Gem.Support.Storehouse_PortraySegmentFormatter;
import link.crystal.Gem.Support.Storehouse_String;
import link.crystal.Gem.Support.Storehouse_StringSegmentFormatter;
import link.crystal.Gem.Support.Temporary_Storehouse_String;
import link.crystal.Gem.Support.World_Integer_Cache;
import link.crystal.Gem.Support.World_Integer_Key;
import link.crystal.Gem.Support.World_Integer_WeakReference;
import link.crystal.Gem.Support.World_String_Cache;
import link.crystal.Gem.Support.World_String_EnduringReference;
import link.crystal.Gem.Support.World_String_Key;
import link.crystal.Gem.Support.World_String_WeakReference;
import link.crystal.Gem.UnitTest.UnitTest_Gem;
import link.crystal.Gem.World.World_Integer;
import link.crystal.Gem.World.World_String;


public final class  Zone
    extends         Gem_Object <Inspection>
//  extends         Object
    implements      Inspectable<Inspection>//,
{
    private static final Inspection     inspection = Inspection.create("Zone");


    //
    //  Static members
    //
    private static /*boot-final*/ Thread    first_thread /* = null */ ;
    private static /*boot-final*/ Zone      first_zone   /* = null */ ;

    private static final int            parse_format_allocated   = 10;
    private static final int            string_builder_allocated = 10;


    //
    //  Members
    //
    public  final Thread                zone_thread;

    private final ParseFormat[]         parse_format_many;
    private /*:*/ int                   parse_format_total;

    private final Gem_StringBuilder[]   string_builder_many;
    private /*:*/ int                   string_builder_total;

    private /*boot-final*/ Map__String__ArgumentSegmentFormatter_Inspection     format_map /* = null */ ;

    public  /*boot-final*/ World_Integer_Key                    integer_key                             /* = null */ ;
    private /*boot-final*/ Storehouse_AdornmentSegmentFormatter storehouse_adornment_segment_formatter  /* = null */ ;
    private /*boot-final*/ Storehouse_MessageFormattable        storehouse_message_formattable          /* = null */ ;
    private /*boot-final*/ Storehouse_NormalSegmentFormatter    storehouse_normal_segment_formatter     /* = null */ ;
    private /*boot-final*/ Storehouse_PortraySegmentFormatter   storehouse_portray_segment_formatter    /* = null */ ;
    private /*boot-final*/ Storehouse_StringSegmentFormatter    storehouse_string_segment_formatter     /* = null */ ;
    public  /*boot-final*/ Storehouse_String__Interface         storehouse_string                       /* = null */ ;
    public  /*boot-final*/ World_String_Key                     string_key                              /* = null */ ;


    //
    //  Constructor & Factory
    //
    private                             Zone(
            final Thread                        zone_thread,
            final ParseFormat[]                 parse_format_many,
            final Gem_StringBuilder[]           string_builder_many//,
        )
    {
        this.zone_thread = zone_thread;

        this.parse_format_many  = parse_format_many;
        this.parse_format_total = 0;

        this.string_builder_many  = string_builder_many;
        this.string_builder_total = 0;

        //
        //  NOTE:
        //      To avoid class initialization loops all the following CANNOT be initialized here.
        //
        //      Each of the following must be initializated when first used
        //      (i.e.: after other class initializations have run)
        //
        //  HENCE:
        //      None of the following can be declared as `final` either ...
        //
        this.integer_key                            = null;
        this.format_map                             = null;
        this.storehouse_adornment_segment_formatter = null;
        this.storehouse_message_formattable         = null;
        this.storehouse_normal_segment_formatter    = null;
        this.storehouse_portray_segment_formatter   = null;
        this.storehouse_string_segment_formatter    = null;
        this.storehouse_string                      = null;
        this.string_key                             = null;
    }


    public static final Zone            create(Thread zone_thread)
    {
        //
        //  NOTE:
        //      See comment in `.current_zone` that says these two calls are apparently "safe" & ok to do during class
        //      initialization.
        //
        final ParseFormat[]             parse_format_many   = new ParseFormat      [Zone.parse_format_allocated];
        final Gem_StringBuilder[]       string_builder_many = new Gem_StringBuilder[Zone.string_builder_allocated];

        return new Zone(zone_thread, parse_format_many, string_builder_many);
    }


    //
    //  Interface Inspectable
    //
    @Override
    public final Inspection             inspect()
    {
        return /*static*/ this.inspection;
    }


    @Override
    public final void                   portray(final Gem_StringBuilder builder)
    {
        builder.augment("<Zone zone_thread{p} ... string_builder_total{p} ...>",
                        this.zone_thread,
                        this.string_builder_total);
    }


    //
    //  Private
    //
    private final void                  boot()
    {
        final Zone                      z = this;

        assert fact_null(this.integer_key,                            "this.integer_key");
        assert fact_null(this.storehouse_adornment_segment_formatter, "this.storehouse_adornment_segment_formatter");
        assert fact_null(this.storehouse_message_formattable,         "this.storehouse_message_formattable");
        assert fact_null(this.storehouse_normal_segment_formatter,    "this.storehouse_normal_segment_formatter");
        assert fact_null(this.storehouse_portray_segment_formatter,   "this.storehouse_portray_segment_formatter");
        assert fact_null(this.storehouse_string_segment_formatter,    "this.storehouse_string_segment_formatter");
        assert fact_null(this.storehouse_string,                      "this.storehouse_string");
        assert fact_null(this.string_key,                             "this.string_key");

        //
        //  NOTE:
        //      `this.storehouse_string` must be initiliazed first, as the others depend on it.
        //
        final Temporary_Storehouse_String   temporary_storehouse_string = this.boot__storehouse_string();

        this.integer_key                            = World_Integer_Key                   .create__ALLY__Zone(z);
        this.storehouse_adornment_segment_formatter = Storehouse_AdornmentSegmentFormatter.create__ALLY__Zone(z);
        this.storehouse_message_formattable         = Storehouse_MessageFormattable       .create__ALLY__Zone(z);
        this.storehouse_normal_segment_formatter    = Storehouse_NormalSegmentFormatter   .create__ALLY__Zone(z);
        this.storehouse_portray_segment_formatter   = Storehouse_PortraySegmentFormatter  .create__ALLY__Zone(z);
        this.storehouse_string_segment_formatter    = Storehouse_StringSegmentFormatter   .create__ALLY__Zone(z);
        this.string_key                             = World_String_Key                    .create__ALLY__Zone(z);

        Gem.boot__ALLY__Zone(z);

        //temporary_storehouse_string.dump("temporary_storehouse_string");
    }


    private final Temporary_Storehouse_String   boot__storehouse_string()
    {
        final Zone                      z = this;

        assert fact_null(this.storehouse_string, "this.storehouse_string");

        final Temporary_Storehouse_String   temporary_storehouse_string = (
                Temporary_Storehouse_String.create__ALLY__Zone(z)
            );

        assert fact_null(this.storehouse_string, "this.storehouse_string");

        this.storehouse_string = temporary_storehouse_string;

        //
        //  NOTE:
        //      Internally the call to `Storehouse_String.create__ALLY__Zone` initializes class `Storehouse_String`
        //      which then via `Inspection.create` calls `.intern_permenant_string`.
        //
        //  HENCE:
        //      We need to have stored `temporary_storehouse_string` in `this.storehouse_string` *BEFORE* the next
        //      line is executed ...
        //
        final Storehouse_String         storehouse_string = Storehouse_String.create__ALLY__Zone(z);

        assert fact(
                 this.storehouse_string == temporary_storehouse_string,
                "this.storehouse_string == temporary_storehouse_string"//,
            );

        this.storehouse_string = storehouse_string;

        for (final String               k : temporary_storehouse_string.keySet()) {
            storehouse_string.insert_permenant_string(z, k);
        }

        return temporary_storehouse_string;
    }


    //
    //  Public (debug)
    //
    public final void                   dump()
    {
        final Gem_StringBuilder[]       string_builder_many  = this.string_builder_many;
        final int                       string_builder_total = this.string_builder_total;

        line("Dump of Zone: {}", this);
        line("  zone_thread: {}", this.zone_thread);

        line("---");
        line("      parse_format_many: {}", parse_format_many);
        line("     parse_format_total: {}", parse_format_total);

        for (/*:*/ int                  i = 0; i < parse_format_total; i ++) {
            line("  parse_format_many[{}]: {}", i, parse_format_many[i]);
        }

        line("---");
        line("     string_builder_many: {}", string_builder_many);
        line("    string_builder_total: {}", string_builder_total);

        for (/*:*/ int                  i = 0; i < string_builder_total; i ++) {
            line("  string_builder_many[{}]: {}", i, string_builder_many[i]);
        }

        line("---");
        line("                             format_map: {}", this.format_map);
        line("                            integer_key: {}", this.integer_key);
        line(" storehouse_adornment_segment_formatter: {}", this.storehouse_adornment_segment_formatter);
        line("         storehouse_message_formattable: {}", this.storehouse_message_formattable);
        line("    storehouse_normal_segment_formatter: {}", this.storehouse_normal_segment_formatter);
        line("   storehouse_portray_segment_formatter: {}", this.storehouse_portray_segment_formatter);
        line("    storehouse_string_segment_formatter: {}", this.storehouse_string_segment_formatter);
        line("                      storehouse_string: {}", this.storehouse_string);
        line("                             string_key: {}", this.string_key);

        //this.storehouse_string.dump("Zone.storehouse_string");
        this.format_map.dump("Zone.format_map");

        line("End of dump of Zone");
    }


    //
    //  Public (parse_format)
    //
    //  NOTE:
    //      Due to possible nested called in a single thread, we might need multiple copies of `parse_format`.
    //
    public final ParseFormat            summon_ParseFormat__ALLY__ParseFormat(final String format)
    {
        /*:*/ int                       parse_format_total = this.parse_format_total;

        if (parse_format_total > 0) {
            parse_format_total -= 1;

            this.parse_format_total = parse_format_total;

            return this.parse_format_many[parse_format_total].recycle(format);
        }


        //
        //  NOTE:
        //      Must allocate `format_map` after initialization -- trying this during class initialization causes
        //      nasty loops.
        //
        final Zone                      z = this;

        Map__String__ArgumentSegmentFormatter_Inspection    format_map = this.format_map;

        if (format_map == null) {
            format_map =
                this.format_map = Map__String__ArgumentSegmentFormatter_Inspection.CREATE_AND_POPULATE(z);
        }


        return ParseFormat.create__ALLY__Zone(z, format, format_map);
    }


    public final void                   recycle__ParseFormat__ALLY__ParseFormat(ParseFormat parse_format)
    {
        final int                       parse_format_total = this.parse_format_total;

        if (parse_format_total < Zone.parse_format_allocated) {
            this.parse_format_many[parse_format_total] = parse_format;

            this.parse_format_total = parse_format_total + 1;
        }
    }


    //
    //  Public (string builder)
    //
    //  NOTE:
    //      See note above in "parse_format" section.
    //
    public final Gem_StringBuilder      summon_StringBuilder()
    {
        /*:*/ int                       string_builder_total = this.string_builder_total;

        if (string_builder_total > 0) {
            string_builder_total -= 1;

            this.string_builder_total = string_builder_total;

            return this.string_builder_many[string_builder_total].recycle();
        }

        final Zone                      z = this;

        return Gem_StringBuilder.create__ALLY__Zone(z);
    }


    public final void                   recycle__StringBuilder__ALLY__Gem_StringBuilder(
            final Gem_StringBuilder             builder//,
        )
    {
        final int                       string_builder_total = this.string_builder_total;

        if (string_builder_total < Zone.string_builder_allocated) {
            this.string_builder_many[string_builder_total] = builder;

            this.string_builder_total = string_builder_total + 1;
        }
    }


    //
    //  Public (current_zone)
    //
    //  NOTE:
    //      Since this is called during class initializatoin, this routine must be very careful not to do much
    //      (in particular, this routiine & the routines it calls `.create` & the constructor) must not do much.
    //
    //  NOTE:
    //      "not do much" includes ... not even looking at another class, as it might cause that class to call
    //      it's class initialization functions, leading to loops ...
    //
    //  NOTE:
    //      Apparently the calls to `new ParseFormat[]` & `new Gem_StringBuilder` in the `.create` function
    //      are safe -- and do not cause loops.
    //
    public static final Zone            current_zone()
    {
        final Thread                    thread = Thread.currentThread();

        if (Zone.first_thread == thread) {
            return Zone.first_zone;
        }

        if (Zone.first_thread != null) {
            //
            //  NOTE:
            //
            //      throw `RuntimeException` directly here, to avoid recursive calls
            //
            //      (since calling `.RUNTIME` might internally call this routine, leading to recursive calls)
            //
            throw new RuntimeException("Zone.current_zone: only single threaded currently supported");
        }

        final Zone                      first_zone = Zone.create(thread);

        Zone.first_thread = thread;
        Zone.first_zone   = first_zone;

        first_zone.boot();

        return first_zone;
    }


    //
    //  Public (conjure: integer)
    //
    public final World_String            conjure_enduring_string(final String s)
    {
        final World_String_Cache         string_cache = Gem.string_cache;

        final World_String_Key           key = this.string_key;

        key.recycle(s);

        final Gem_ComparableReference_Interface<
                  ? extends Gem_Reference_Inspection,
                  World_String,
                  Comparable_Inspection
              >                          previous = string_cache.get(key);

        /*final*/ World_String          client;

        if (previous == null) {
            client = World_String.create__ALLY__Gem(s);
        } else {
            client = previous.client_OR_enqueue();

            if (client == null) {
                Gem.reference_queue.cleanup();

                assert fact(string_cache.get(key) == null, "world_string_cache.get({}) == null", key);

                client = World_String.create__ALLY__Gem(s);
            } else {
                assert fact(s.equals(client.s), "s.equals(client.s)");

                if (previous.inspect().is_enduring_reference) {
                    return client;
                }

                final UnitTest_Gem      unit_test = Gem.unit_test;

                if (unit_test != null) {
                    unit_test.discarding__World_String_WeakReference((World_String_WeakReference) previous);
                }


                //
                //  NOTE:
                //      Have to remove the 'previous' key (the weak reference), so that a new key (and new value) can
                //      be `put`.
                //
                //      Be default `put` *ONLY* replaces the value, and not the key; hence the need to remove the key
                //      first.
                //
                string_cache.remove(previous);
            }
        }

        final World_String_EnduringReference    enduring_reference = (
                World_String_EnduringReference.create__ALLY__Gem(client)
            );

        string_cache.put(enduring_reference, enduring_reference);

        return client;
    }


    public final World_Integer          conjure_integer(final int value)
    {
        final World_Integer_Cache       integer_cache = Gem.integer_cache;

        final World_Integer_Key         key = this.integer_key;

        key.recycle(value);

        final Gem_ComparableReference_Interface<
                  ? extends Gem_Reference_Inspection,
                  World_Integer,
                  Comparable_Inspection
              >                         previous = integer_cache.get(key);

        if (previous != null) {
            final World_Integer         client = previous.client_OR_enqueue();

            if (client != null) {
                assert fact(value == client.value, "value == client.value");

                return client;
            }

            Gem.reference_queue.cleanup();

            assert fact(integer_cache.get(key) == null, "world_integer_cache.get({}) == null", key);
        }

        final World_Integer             r = World_Integer.create__ALLY__Gem(value);

        final World_Integer_WeakReference   weak_reference = (
                World_Integer_WeakReference.create__ALLY__Gem(r, Gem.reference_queue)
            );

        integer_cache.put(weak_reference, weak_reference);

        return r;
    }


    public final World_String           conjure_string(final String s)
    {
        final World_String_Cache        string_cache = Gem.string_cache;

        final World_String_Key          key = this.string_key;

        key.recycle(s);

        final Gem_ComparableReference_Interface<
                  ? extends Gem_Reference_Inspection,
                  World_String,
                  Comparable_Inspection
              >                         previous = string_cache.get(key);

        if (previous != null) {
            final World_String          client = previous.client_OR_enqueue();

            if (client != null) {
                assert fact(s.equals(client.s), "s.equals(client.s)");

                return client;
            }

            Gem.reference_queue.cleanup();

            assert fact(string_cache.get(key) == null, "world_string_cache.get({}) == null", key);
        }

        final World_String              r = World_String.create__ALLY__Gem(s);

        final World_String_WeakReference    weak_reference = (
                World_String_WeakReference.create__ALLY__Gem(r, Gem.reference_queue)
            );

        string_cache.put(weak_reference, weak_reference);

        return r;
    }


    //
    //  Public (conjure: formatting)
    //
    public final AdornmentSegmentFormatter  conjure_AdornmentSegmentFormatter(final String s)
    {
        final Zone                      z = this;

        Storehouse_AdornmentSegmentFormatter    storehouse_adornment_segment_formatter = (
                this.storehouse_adornment_segment_formatter
            );

        assert fact_pointer(storehouse_adornment_segment_formatter, "storehouse_adornment_segment_formatter");

        final AdornmentSegmentFormatter     previous = storehouse_adornment_segment_formatter.lookup(z, s);

        if (previous != null) {
            return previous;
        }

        final AdornmentSegmentFormatter     r = AdornmentSegmentFormatter.create__ALLY__Zone(z, s);

        storehouse_adornment_segment_formatter.insert(z, r.s, r);

        return r;
    }


    public final NormalSegmentFormatter     conjure_NormalSegmentFormatter(final int argument_index)
    {
        final Zone                      z = this;

        final Storehouse_NormalSegmentFormatter     storehouse_normal_segment_formatter = (
                this.storehouse_normal_segment_formatter
            );

        assert fact_pointer(storehouse_normal_segment_formatter, "storehouse_normal_segment_formatter");

        final NormalSegmentFormatter    previous = storehouse_normal_segment_formatter.lookup(z, argument_index);

        if (previous != null) {
            return previous;
        }

        final NormalSegmentFormatter    r = NormalSegmentFormatter.create__ALLY__Zone(z, argument_index);

        storehouse_normal_segment_formatter.insert(z, argument_index, r);

        return r;
    }


    public final PortraySegmentFormatter    conjure_PortraySegmentFormatter(final int argument_index)
    {
        final Zone                      z = this;

        final Storehouse_PortraySegmentFormatter    storehouse_portray_segment_formatter = (
                this.storehouse_portray_segment_formatter
            );

        assert fact_pointer(storehouse_portray_segment_formatter, "storehouse_portray_segment_formatter");

        final PortraySegmentFormatter   previous = storehouse_portray_segment_formatter.lookup(z, argument_index);

        if (previous != null) {
            return previous;
        }

        final PortraySegmentFormatter   r = PortraySegmentFormatter.create__ALLY__Zone(z, argument_index);

        storehouse_portray_segment_formatter.insert(z, argument_index, r);

        return r;
    }


    public final Storehouse_MessageFormattable  conjure__Storehouse_MessageFormattable()
    {
        final Storehouse_MessageFormattable   storehouse_message_formattable = this.storehouse_message_formattable;

        assert fact_pointer(storehouse_message_formattable, "storehouse_message_formattable");

        return storehouse_message_formattable;
    }


    public final StringSegmentFormatter     conjure_StringSegmentFormatter(final int argument_index)
    {
        final Zone                      z = this;

        final Storehouse_StringSegmentFormatter     storehouse_string_segment_formatter = (
                this.storehouse_string_segment_formatter
            );

        assert fact_pointer(storehouse_string_segment_formatter, "storehouse_string_segment_formatter");

        final StringSegmentFormatter    previous = storehouse_string_segment_formatter.lookup(z, argument_index);

        if (previous != null) {
            return previous;
        }

        final StringSegmentFormatter    r = StringSegmentFormatter.create__ALLY__Zone(z, argument_index);

        storehouse_string_segment_formatter.insert(z, argument_index, r);

        return r;
    }


    //
    //  Public (other)
    //
    public final String                 intern_permenant_string(final String s)
    {
        final Zone                      z = this;

        return this.storehouse_string.intern_permenant_string(z, s);
    }


    public final String                 quote_string(final String s)
    {
        final Gem_StringBuilder         builder = this.summon_StringBuilder();

        builder.java_quote(s);

        return builder.finish_AND_recycle();
    }
}
