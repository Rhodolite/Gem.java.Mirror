//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Gem.Support;


import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import link.crystal.Gem.Core.Gem;
import link.crystal.Gem.Core.Gem_Object;
import link.crystal.Gem.Core.Gem_StringMap;
import link.crystal.Gem.Core.Zone;
import link.crystal.Gem.Inspection.Inspection;
import link.crystal.Gem.Interface.Inspectable;


public final class  Inspection_Cache
    extends         Gem_StringMap  <Inspection,         Inspection>
//  extends         Gem_Map        <Inspection, String, Inspection>
//  extends         HashMap                    <String, Inspection>
//  extends         AbstractHashMap            <String, Inspection>
//  extends         Object
    implements      Inspectable<Inspection>//,
{
    private static final Inspection     inspection = Inspection.create_cache("Inspection_Cache");


    //
    //  Static members
    //
    //  IMPORTANT NOTE:
    //      Do *NOT* initialize either `cache` or `cache_index`, as due to class initialization order, this *could* cause
    //      them to overwrite the values allocated in `.insert_or_cache`
    //
    //      I.E.:  `.insert_or_cache` can run, and then after running, it could execute these statements.
    //
    private static final int            initial_capacity = 101;
    private static /*:*/ Inspection[]   cache         /* = null */ ;
    private static final int            cache_allocated  = 20;
    private static  /*:*/int            cache_index   /* = 0 */ ;


    //
    //  Constructor & Factory
    //
    private                             Inspection_Cache(final Zone z, final int initial_capacity)
    {
        super(z, initial_capacity);
    }


    private static Inspection_Cache         create(final Zone z)
    {
        return new Inspection_Cache(z, Inspection_Cache.initial_capacity);
    }


    public static final Inspection_Cache    create__ALLY__Gem(final Zone z)
    {
        return new Inspection_Cache(z, Inspection_Cache.initial_capacity);
    }


    //
    //  Interface Inspectable
    //
    @Override
    public final Inspection             inspect()
    {
        return /*static*/ this.inspection;
    }


    //
    //  Ally
    //
    public final void                   boot__ALLY__Zone(final Zone z)
    {
        //
        //  Clear the cache ...
        //
        final Inspection[]              cache       = Inspection_Cache.cache;
        final int                       cache_index = Inspection_Cache.cache_index;

        for (/*:*/ int                  i        = 0; i < cache_index; i ++) {
            final Inspection            previous = cache[i];

            this.insert(z, previous.simple_class_name, previous);
        }

        Inspection_Cache.cache       = null;
        Inspection_Cache.cache_index = 0;
    }


    //
    //  Public
    //
    public final void                   dump(final String name)
    {
        final Inspection                inspection = this.inspect();

        final String                    simple_class_name = inspection.simple_class_name;

        final ArrayList<Inspection>     values = new ArrayList<Inspection>(this.values());

        Collections.sort(values);

        final int                       total = values.size();

        line("Dump of {}", simple_class_name + " " + name);
        line("  size: {}", total);

        for (/*:*/ int                  i = 0; i < total; i ++) {
            final Inspection            v = values.get(i);

            line("  {}", v);
        }

        line("End of dump of {}", simple_class_name + " " + name);
    }


    public static final void            insert_or_cache(Inspection v)
    {
        final Inspection_Cache          inspection_cache = Gem.inspection_cache;

        if (inspection_cache != null) {
            final Zone                  z = inspection_cache.z;

            inspection_cache.insert(z, v.simple_class_name, v);

            return;
        }

        //
        //  NOTE:
        //      `Gem.inspection_cache` has not yet been initialized ... so temporarily cache `v`
        //
        /*:*/ Inspection[]              cache           = Inspection_Cache.cache;
        final int                       cache_allocated = Inspection_Cache.cache_allocated;
        final int                       cache_index     = Inspection_Cache.cache_index;

        if (cache == null) {
            cache =
                Inspection_Cache.cache = new Inspection[cache_allocated];
        }

        assert fact(cache_index < cache_allocated, "cache_index < cache_allocated");

        //output("Caching: " + v.simple_class_name);

        cache[cache_index] = v;

        Inspection_Cache.cache_index = cache_index + 1;
    }
}
