//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Gem.Format;


import link.crystal.Gem.Core.Gem_StringBuilder;
import link.crystal.Gem.Core.Zone;
import link.crystal.Gem.Format.ArgumentSegmentFormatter;
import link.crystal.Gem.Inspection.Inspection;
import link.crystal.Gem.Inspection.World_Inspection;
import link.crystal.Gem.Interface.Inspectable;


//
//  NOTE:
//      This is not an extension of `Comparable_Inspection` because, at present, SegmentFormatters are not comparable
//      (They might become comparable in the future).
//
public /*:*/ class  SegmentFormatter_Inspection
    extends         Inspection
//  extends         Gem_Object <World_Inspection>
//  extends         Object
    implements      Inspectable<World_Inspection>//,
{
    private static final World_Inspection   inspection = World_Inspection.create("SegmentFormatter_Inspection");


    //
    //  Members
    //
    public final boolean                is_adornment_segment_formatter;
    public final boolean                is_portray_segment_formatter;


    //
    //  Constructor & Factory
    //
    protected                           SegmentFormatter_Inspection(final String simple_class_name)
    {
        super(simple_class_name, /*is_cache=*/ false);

        this.is_adornment_segment_formatter = simple_class_name.equals("AdornmentSegmentFormatter");
        this.is_portray_segment_formatter   = simple_class_name.equals("PortraySegmentFormatter");
    }


    public static /*overrideable*/ SegmentFormatter_Inspection  create(final String simple_class_name)
    {
        final Zone                      z = Zone.current_zone();

        final String                    interned__simple_class_name = z.intern_permenant_string(simple_class_name);

        return new SegmentFormatter_Inspection(interned__simple_class_name);
    }


    //
    //  Interface Inspectable
    //
    @Override
    public /*overrideable*/ World_Inspection   inspect()
    {
        return /*static*/ this.inspection;
    }


    @Override
    public final void                   portray(final Gem_StringBuilder builder)
    {
        final World_Inspection          meta_inspection = this.inspect();

        builder.append("<", meta_inspection.simple_class_name, " ", this.simple_class_name);

        if (this.is_adornment_segment_formatter) {
            builder.append("; is_adornment_segment_formatter");
        }

        if (this.is_portray_segment_formatter) {
            builder.append("; is_portray_segment_formatter");
        }

        builder.append(">");
    }
}
