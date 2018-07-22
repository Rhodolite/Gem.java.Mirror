//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Gem.Inspection;


import java.lang.Comparable;
import link.crystal.Gem.Core.Gem_Object;
import link.crystal.Gem.Core.Gem_StringBuilder;
import link.crystal.Gem.Core.Zone;
import link.crystal.Gem.Inspection.Comparable_Inspection;
import link.crystal.Gem.Inspection.World_Inspection;
import link.crystal.Gem.Interface.Gem_Comparable;
import link.crystal.Gem.Interface.Inspectable;
import link.crystal.Gem.Support.Inspection_Cache;


//
//  NOTE:
//      The "meta" class of `Inspection` is `World_Inspection`
//
public /*:*/ class  Inspection
    extends         Gem_Object    <World_Inspection>
//  extends         Object
    implements      Gem_Comparable<World_Inspection>,
                    Comparable<Gem_Comparable<? extends Comparable_Inspection>>,    //  Via Gem_Comparable
                    Inspectable   <World_Inspection>//,
{
    private static final World_Inspection   inspection = World_Inspection.create("Inspection");


    //
    //  Members
    //
    public final String                 simple_class_name;
    public final boolean                is_cache;


    //
    //  Constructor & Factory
    //
    protected                           Inspection(final String simple_class_name, boolean is_cache)
    {
        this.simple_class_name = simple_class_name;
        this.is_cache          = is_cache;

        Inspection_Cache.insert_or_cache(this);
    }


    public static /*overrideable*/ Inspection   create(final String simple_class_name)
    {
        final Zone                      z = Zone.current_zone();

        final String                    interned__simple_class_name = z.intern_permenant_string(simple_class_name);

        return new Inspection(interned__simple_class_name, /*is_cache=*/ false);
    }


    public static /*overrideable*/ Inspection   create_cache(final String simple_class_name)
    {
        final Zone                      z = Zone.current_zone();

        final String                    interned__simple_class_name = z.intern_permenant_string(simple_class_name);

        return new Inspection(interned__simple_class_name, /*is_cache=*/ true);
    }


    //
    //  Interface java.lang.Comparable
    //
    @Override
    public final int                    compareTo(Gem_Comparable<? extends Comparable_Inspection> that)
    {
        final int                       class_compare = (
                Comparable_Inspection.CLASS_ORDER__INSPECTION - that.inspect().class_order
            );

        if (class_compare != 0) {
            return class_compare;
        }

        final Inspection                that_2 = (Inspection) that;

        final int                       r = this.simple_class_name.compareTo(that_2.simple_class_name);

        assert fact(r != 0, "r != 0");

        return r;
    }


    //
    //  Interface Gem_Comparable
    //
    //<empty>


    //
    //  Interface Inspectable
    //
    //  NOTE:
    //      Includes extra helper function `portray_prefix` which is *NOT* part of `Interface Inspectable`
    //
    @Override
    public /*overrideable*/ World_Inspection    inspect()
    {
        return /*static*/ this.inspection;
    }


    @Override
    public /*overrideable*/ void        portray(final Gem_StringBuilder builder)
    {
        this.portray_prefix(builder);
        builder.append(">");
    }


    public /*overrideable*/ void        portray_prefix(final Gem_StringBuilder builder)
    {
        assert fact_pointer(this,    "this");
        assert fact_pointer(builder, "builder");

        final World_Inspection          meta_inspection = this.inspect();

        //
        //  NOTE:
        //      During startup initialization, it could be that any (or all) of the following could be null:
        //
        //      1.  `meta_inspection`,
        //      2.  `meta_inspection.simple_class_name`, AND/OR
        //      3.  `this.simple_class_name`
        //
        builder.append(
            "<",
            (
                (meta_inspection == null || meta_inspection.simple_class_name == null)
                    ? "<INSPECTION extends World_Inspection>"
                    : meta_inspection.simple_class_name
            ),
            " ",
            (
                this.simple_class_name == null
                    ? "<INSPECTION extends Inspection>"
                    : this.simple_class_name
            )//,
        );

        if (this.is_cache) {
            builder.append("; is_cache");
        }
    }
}
