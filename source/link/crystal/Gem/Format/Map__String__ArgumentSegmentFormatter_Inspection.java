//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Gem.Format;


import java.lang.String;
import link.crystal.Gem.Core.Gem_StringMap;
import link.crystal.Gem.Core.Zone;
import link.crystal.Gem.Format.ArgumentSegmentFormatter_Inspection;
import link.crystal.Gem.Format.NormalSegmentFormatter;
import link.crystal.Gem.Format.PortraySegmentFormatter;
import link.crystal.Gem.Format.StringSegmentFormatter;
import link.crystal.Gem.Inspection.Inspection;
import link.crystal.Gem.Interface.Inspectable;


public final class  Map__String__ArgumentSegmentFormatter_Inspection
    extends         Gem_StringMap  <Inspection,         ArgumentSegmentFormatter_Inspection>
//  extends         Gem_Map        <Inspection, String, ArgumentSegmentFormatter_Inspection>
//  extends         HashMap                    <String, ArgumentSegmentFormatter_Inspection>
//  extends         AbstractHashMap            <String, ArgumentSegmentFormatter_Inspection>
//  extends         Object
    implements      Inspectable<Inspection>//,
{
    private static final Inspection     inspection = (
            Inspection.create("Map__String__ArgumentSegmentFormatter_Inspection")
        );


    //
    //  Static members
    //
    private static final int            initial_capacity = 11;



    //
    //  Constructor & Factory
    //
    private                             Map__String__ArgumentSegmentFormatter_Inspection(
            final Zone                          z,
            final int                           initial_capacity//,
        )
    {
        super(z, initial_capacity);
    }


    public static final Map__String__ArgumentSegmentFormatter_Inspection    CREATE_AND_POPULATE(final Zone z)
    {
        Map__String__ArgumentSegmentFormatter_Inspection    r = (
                new Map__String__ArgumentSegmentFormatter_Inspection(
                    z,
                    Map__String__ArgumentSegmentFormatter_Inspection.initial_capacity//,
                )
            );

        r.put("",  NormalSegmentFormatter .inspection);
        r.put("p", PortraySegmentFormatter.inspection);
        r.put("s", StringSegmentFormatter .inspection);

        return r;
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
