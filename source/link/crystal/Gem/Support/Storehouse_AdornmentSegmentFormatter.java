//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Gem.Support;


import java.lang.String;
import link.crystal.Gem.Core.Gem_StringMap;
import link.crystal.Gem.Core.Zone;
import link.crystal.Gem.Format.AdornmentSegmentFormatter;
import link.crystal.Gem.Inspection.Inspection;
import link.crystal.Gem.Interface.Inspectable;


public final class  Storehouse_AdornmentSegmentFormatter
    extends         Gem_StringMap  <Inspection,         AdornmentSegmentFormatter>
//  extends         Gem_Map        <Inspection, String, AdornmentSegmentFormatter>
//  extends         HashMap                    <String, AdornmentSegmentFormatter>
//  extends         AbstractHashMap            <String, AdornmentSegmentFormatter>
//  extends         Object
    implements      Inspectable    <Inspection>//,
{
    private static final Inspection     inspection = Inspection.create("Storehouse_AdornmentSegmentFormatter");


    //
    //  Static members
    //
    private static final int            initial_capacity = 1009;


    //
    //  Constructor & Factory
    //
    private                             Storehouse_AdornmentSegmentFormatter(final Zone z, final int initial_capacity)
    {
        super(z, initial_capacity);
    }


    public static final Storehouse_AdornmentSegmentFormatter    create__ALLY__Zone(final Zone z)
    {
        return new Storehouse_AdornmentSegmentFormatter(z, Storehouse_AdornmentSegmentFormatter.initial_capacity);
    }


    //
    //  Interface Inspectable
    //
    @Override
    public final Inspection             inspect()
    {
        return /*static*/ this.inspection;
    }
}
