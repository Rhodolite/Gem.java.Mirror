//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Gem.Format;


import java.lang.String;
import link.crystal.Gem.Core.Gem_StringBuilder;
import link.crystal.Gem.Core.Zone;
import link.crystal.Gem.Format.ArgumentSegmentFormatter;
import link.crystal.Gem.Format.ArgumentSegmentFormatter_Inspection;
import link.crystal.Gem.Inspection.World_Inspection;
import link.crystal.Gem.Interface.Inspectable;
import link.crystal.Gem.Interface.MessageFormattable;
import link.crystal.Gem.Interface.SegmentFormattable;


public final class  StringSegmentFormatter
    extends         ArgumentSegmentFormatter<StringSegmentFormatter_Inspection>
//  extends         MessageFormatter_Base   <StringSegmentFormatter_Inspection>
//  extends         Gem_Object              <StringSegmentFormatter_Inspection>
//  extends         Object
    implements      MessageFormattable      <StringSegmentFormatter_Inspection>,
                    SegmentFormattable      <StringSegmentFormatter_Inspection>,
                    Inspectable             <StringSegmentFormatter_Inspection>//,
{
    public static final StringSegmentFormatter_Inspection   inspection = (
            StringSegmentFormatter_Inspection.create("StringSegmentFormatter")
        );


    //
    //  Constructor & Factory
    //
    private                             StringSegmentFormatter(final int argument_index)
    {
        super(argument_index);
    }


    public static final StringSegmentFormatter    create__ALLY__Zone(final Zone z, final int argument_index)
    {
        return new StringSegmentFormatter(argument_index);
    }


    //
    //  Interface Inspectable
    //
    @Override
    public final StringSegmentFormatter_Inspection  inspect()
    {
        return /*static*/ this.inspection;
    }


    //
    //  Interface MessageFormattable
    //
    @Override
    public final String                 augment(final int depth, final Object v)
    {
        return v.toString();
    }


    @Override
    public final void                   augment(final Gem_StringBuilder builder, int depth, final Object v)
    {
        builder.append(v.toString());
    }


    @Override
    public final void                   augment_1_plus(
            final Gem_StringBuilder             builder,
            final int                           depth,
            final Object                        v,
            final Object ...                    other_arguments//,
        )
    {
        if (other_arguments.length != 0) {
            RUNTIME("{p} argument{} given (expected 0)",
                    other_arguments.length,
                    (other_arguments.length == 1 ? "" : "s"));
        }

        builder.append(v.toString());
    }


    //
    //  Interface SegmentFormattable
    //
    @Override
    public final void                   choose(final Gem_StringBuilder builder, int depth)
    {
        INVALID_ROUTINE();
    }


    @Override
    public final void                   choose(final Gem_StringBuilder builder, int depth, final Object v)
    {
        final int                       argument_index = this.argument_index;

        if (argument_index == 0) {
            builder.append(v.toString());
            return;
        }

        RUNTIME("argument_index is {} (expected 0)", argument_index);
    }


    @Override
    public final void                   choose(
            final Gem_StringBuilder             builder,
            final int                           depth,
            final Object                        v,
            final Object                        w//,
        )
    {
        final int                       argument_index = this.argument_index;

        if (argument_index == 0) {
            builder.append(v.toString());
            return;
        }

        if (argument_index == 1) {
            builder.append(w.toString());
            return;
        }

        RUNTIME("argument_index is {} (expected 0 or 1)", argument_index);
    }


    @Override
    public final void                   choose(
            final Gem_StringBuilder             builder,
            final int                           depth,
            final Object                        v,
            final Object                        w,
            final Object                        x//,
        )
    {
        final int                       argument_index = this.argument_index;

        if (argument_index == 0) {
            builder.append(v.toString());
            return;
        }

        if (argument_index == 1) {
            builder.append(w.toString());
            return;
        }

        if (argument_index == 2) {
            builder.append(x.toString());
            return;
        }

        RUNTIME("argument_index is {} (expected 0, 1, or 2)", argument_index);
    }


    @Override
    public final void                   choose(
            final Gem_StringBuilder             builder,
            final int                           depth,
            final Object                        v,
            final Object                        w,
            final Object                        x,
            final Object                        y//,
        )
    {
        final int                       argument_index = this.argument_index;

        if (argument_index == 0) {
            builder.append(v.toString());
            return;
        }

        if (argument_index == 1) {
            builder.append(w.toString());
            return;
        }

        if (argument_index == 2) {
            builder.append(x.toString());
            return;
        }

        if (argument_index == 3) {
            builder.append(y.toString());
            return;
        }

        RUNTIME("argument_index is {} (expected number between 0 and 3)", argument_index);
    }


    @Override
    public final void                   choose(
            final Gem_StringBuilder             builder,
            final int                           depth,
            final Object                        v,
            final Object                        w,
            final Object                        x,
            final Object                        y4,
            final Object                        y5//,
        )
    {
        final int                       argument_index = this.argument_index;

        if (argument_index == 0) {
            builder.append(v.toString());
            return;
        }

        if (argument_index == 1) {
            builder.append(w.toString());
            return;
        }

        if (argument_index == 2) {
            builder.append(x.toString());
            return;
        }

        if (argument_index == 3) {
            builder.append(y4.toString());
            return;
        }

        if (argument_index == 4) {
            builder.append(y5.toString());
            return;
        }

        RUNTIME("argument_index is {} (expected number between 0 and 4)", argument_index);
    }


    @Override
    public final void                   choose(
            final Gem_StringBuilder             builder,
            final int                           depth,
            final Object                        v,
            final Object                        w,
            final Object                        x,
            final Object                        y4,
            final Object                        y5,
            final Object                        y6//,
        )
    {
        final int                       argument_index = this.argument_index;

        if (argument_index == 0) {
            builder.append(v.toString());
            return;
        }

        if (argument_index == 1) {
            builder.append(w.toString());
            return;
        }

        if (argument_index == 2) {
            builder.append(x.toString());
            return;
        }

        if (argument_index == 3) {
            builder.append(y4.toString());
            return;
        }

        if (argument_index == 4) {
            builder.append(y5.toString());
            return;
        }

        if (argument_index == 5) {
            builder.append(y6.toString());
            return;
        }

        RUNTIME("argument_index is {} (expected number between 0 and 5)", argument_index);
    }


    @Override
    public final void                   choose(
            final Gem_StringBuilder             builder,
            final int                           depth,
            final Object                        v,
            final Object                        w,
            final Object                        x,
            final Object                        y4,
            final Object                        y5,
            final Object                        y6,
            final Object                        y7,
            final Object ...                    other_arguments//,
        )
    {
        final int                       argument_index = this.argument_index;

        if (argument_index == 0) {
            builder.append(v.toString());
            return;
        }

        if (argument_index == 1) {
            builder.append(w.toString());
            return;
        }

        if (argument_index == 2) {
            builder.append(x.toString());
            return;
        }

        if (argument_index == 3) {
            builder.append(y4.toString());
            return;
        }

        if (argument_index == 4) {
            builder.append(y5.toString());
            return;
        }

        if (argument_index == 5) {
            builder.append(y6.toString());
            return;
        }

        if (argument_index == 6) {
            builder.append(y7.toString());
            return;
        }

        builder.append(other_arguments[argument_index - 7].toString());
    }
}


class           StringSegmentFormatter_Inspection
    extends     ArgumentSegmentFormatter_Inspection<StringSegmentFormatter>
//  extends     Inspection
//  extends     Gem_Object <World_Inspection>
//  extends     Object
    implements  Inspectable<World_Inspection>//,
{
    private static final World_Inspection   inspection = World_Inspection.create("StringSegmentFormatter_Inspection");


    //
    //  Constructor & Factory
    //
    protected                           StringSegmentFormatter_Inspection(final String simple_class_name)
    {
        super(simple_class_name);
    }


    public static final StringSegmentFormatter_Inspection   create(final String simple_class_name)
    {
        final Zone                      z = Zone.current_zone();

        final String                    interned__simple_class_name = z.intern_permenant_string(simple_class_name);

        return new StringSegmentFormatter_Inspection(interned__simple_class_name);
    }


    //
    //  Abstract ArgumentSegmentFormatter_Inspection
    //
    @Override
    public StringSegmentFormatter   conjure_argument_segment(final Zone z, final int argument_index)
    {
        return z.conjure_StringSegmentFormatter(argument_index);
    }


    //
    //  Interface Inspectable
    //
    @Override
    public final World_Inspection       inspect()
    {
        return /*static*/ this.inspection;
    }
}
