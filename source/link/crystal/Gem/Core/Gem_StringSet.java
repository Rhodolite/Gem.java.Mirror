//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Gem.Core;


import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import link.crystal.Gem.Core.Gem_StringBuilder;
import link.crystal.Gem.Core.Zone;
import link.crystal.Gem.Inspection.Inspection;
import link.crystal.Gem.Interface.Inspectable;


public abstract class   Gem_StringSet<INSPECTION extends Inspection>
    extends             Gem_Map<INSPECTION, String, String>
//  extends             HashMap            <String, String>
//  extends             AbstractHashMap    <String, String>
//  extends             Object
    implements          Inspectable<INSPECTION>//,
{
    //
    //  Constructor
    //
    protected                           Gem_StringSet(final Zone z, final int initial_capacity)
    {
        super(z, initial_capacity);
    }


    //
    //  Interface Inspectable
    //
    @Override
    public final void                   portray(final Gem_StringBuilder builder)
    {
        builder.append("<", this.inspect().simple_class_name, " size<", this.size(), ">>");
    }


    //
    //  Public
    //
    public final void                   dump(final String name)
    {
        final String                    simple_class_name = this.inspect().simple_class_name;

        final List<String>              keys = new ArrayList<String>(this.keySet());

        Collections.sort(keys);

        final int                       total = keys.size();

        line("Dump of {} {}", simple_class_name, name);
        line("      size: {}", total);

        for (/*:*/ int                  i = 0; i < total; i ++) {
            final String                k = keys.get(i);

            line("  {p}", k);
        }

        line("End of dump of {} {}", simple_class_name, name);
    }
}
