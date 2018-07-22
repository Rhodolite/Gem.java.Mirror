//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Gem.Portray_1;


import java.lang.Character;
import java.lang.String;
import link.crystal.Gem.Core.Gem_Object;
import link.crystal.Gem.Core.Gem_StringBuilder;
import link.crystal.Gem.Core.Zone;
import link.crystal.Gem.Inspection.Inspection;
import link.crystal.Gem.Interface.Inspectable;
import link.crystal.Gem.Portray_1.AsciiTable;
import link.crystal.Gem.Portray_1.BuildStringState;


//
//   KA = backslash_with_apostrophe         (can use python `String.__repr__` for optimization)
//   KQ = backslash_with_quotation_mark     (can use python `String.__repr__` for optimization)
//
//   KC = backslash_with_triple_apostrophe
//   KS = backslash_with_triple_quotation_mark
//
//   PA = normal_with_apostrophe            (can use python `String.__repr__` for optimization)
//   PQ = normal_with_quotation_mark        (can use python `String.__repr__` for optimization)
//
//   PC = normal_with_triple_apostrophe
//   PS = normal_with_triple_quotation_mark
//
//   RA = raw_with_apostrophe
//   RQ = raw_with_quotation_mark
//
//   RC = raw_with_triple_apostrophe
//   RS = raw_with_triple_quotation_mark
//
public abstract class   PortrayString
    extends             Gem_Object <Inspection>
    implements          Inspectable<Inspection>//,
{
    //
    //  Static members
    //
    static private /*:*/ int            next_portray_index = 0;


    //
    //  Static members (instances)
    //
    public static final PortrayString   portray_string__invalid = (
            PortrayString_Backslash.create__ALLY__PortrayString(
                "0",
                "'",
                "'",
                AsciiTable.table_with_boring_quotation_mark//,
            )
        );

    public static final PortrayString   backslash_with_apostrophe = (
            PortrayString_Backslash.create__ALLY__PortrayString(
                "KA",
                "'",
                "'",
                AsciiTable.table_with_boring_quotation_mark//,
            )
        );

    public static final PortrayString   backslash_with_quotation_mark = (
            PortrayString_Backslash.create__ALLY__PortrayString(
                "KQ",
                "\"",
                "\"",
                AsciiTable.table_with_boring_apostrophe//,
            )
        );

    public static final PortrayString   backslash_with_triple_apostrophe = (
            PortrayString_TripleWithBackslash.create__ALLY__PortrayString(
                "KC",
                "'''",
                AsciiTable.table_with_boring_quotation_mark,
                39,                                                             //  39 = ordinal("'")
                BuildStringState.A_Start,
                BuildStringState.A_Normal//,
            )
        );

    public static final PortrayString   backslash_with_triple_quotation_mark = (
            PortrayString_TripleWithBackslash.create__ALLY__PortrayString(
                "KS",
                "\"\"\"",
                AsciiTable.table_with_boring_apostrophe,
                34,                                                             //  34 = ordinal('"')
                BuildStringState.Q_Start,
                BuildStringState.Q_Normal//,
            )
        );

    public static final PortrayString   normal_with_apostrophe = (
            PortrayString_Normal.create__ALLY__PortrayString("PA", "'", "'")
        );

    public static final PortrayString   normal_with_quotation_mark = (
            PortrayString_Normal.create__ALLY__PortrayString("PQ", "\"", "\"")
        );

    public static final PortrayString   normal_with_triple_apostrophe = (
            PortrayString_Normal.create__ALLY__PortrayString("PC", "'''", "'''")
        );

    public static final PortrayString   normal_with_triple_quotation_mark = (
            PortrayString_Normal.create__ALLY__PortrayString("PS", "\"\"\"", "\"\"\"")
        );

    public static final PortrayString   raw_with_apostrophe = (
            PortrayString_Normal.create__ALLY__PortrayString("RA", "r'", "'")
        );

    public static final PortrayString   raw_with_quotation_mark = (
            PortrayString_Normal.create__ALLY__PortrayString("RQ", "r\"", "\"")
        );

    public static final PortrayString   raw_with_triple_apostrophe = (
            PortrayString_Normal.create__ALLY__PortrayString("RC", "r'''", "'''")
        );

    public static final PortrayString   raw_with_triple_quotation_mark = (
            PortrayString_Normal.create__ALLY__PortrayString("RS", "r\"\"\"", "\"\"\"")
        );


    //
    //  Members
    //
    public final int                    portray_index;
    public final boolean                is_valid;
    public final String                 abbreviation;


    //
    //  Constructor
    //
    protected                           PortrayString(final String abbreviation)
    {
        final Zone                      z = Zone.current_zone();

        final String                    interned_abbreviation = z.intern_permenant_string(abbreviation);

        if (interned_abbreviation.equals("0")) {
            this.portray_index = -1;
            this.is_valid      = false;
        } else {
            final int                   next_portray_index = PortrayString.next_portray_index;

            this.portray_index = next_portray_index;
            this.is_valid      = true;

            PortrayString.next_portray_index = next_portray_index + 1;
        }

        this.abbreviation = interned_abbreviation;
    }


    //
    //  Interface Inspectable
    //
    //  NOTE:
    //      Includes extra helper function `portray_prefix` which is *NOT* part of `Interface Inspectable`
    //
    public abstract Inspection          inspect();
    public abstract void                portray(final Gem_StringBuilder builder);


    public final void                   portray_prefix(final Gem_StringBuilder builder)
    {
        builder.append("<", this.inspect().simple_class_name);

        if (this.is_valid) {
            builder.append(" ", this.portray_index);
        } else {
            builder.append(" invalid");
        }

        builder.append(" ", this.abbreviation, ";");
    }


    //
    //  Abstract <me>
    //
    public abstract void                portray_string(final Gem_StringBuilder builder, final String s);


    public /*overrideable*/ String      portray_string(final String s)
    {
        final Zone                      z = Zone.current_zone();

        final Gem_StringBuilder         builder = z.summon_StringBuilder();

        this.portray_string(builder, s);

        return builder.finish_AND_recycle();
    }


    //
    //  Public (debug)
    //
    public static final void            dump()
    {
        line("Dump of PortrayString");

        line("   0:  {p}", PortrayString.portray_string__invalid);
        line("  KA:  {p}", PortrayString.backslash_with_apostrophe);
        line("  KQ:  {p}", PortrayString.backslash_with_quotation_mark);
        line("  KC:  {p}", PortrayString.backslash_with_triple_apostrophe);
        line("  KS:  {p}", PortrayString.backslash_with_triple_quotation_mark);
        line("  PA:  {p}", PortrayString.normal_with_apostrophe);
        line("  PQ:  {p}", PortrayString.normal_with_quotation_mark);
        line("  PC:  {p}", PortrayString.normal_with_triple_apostrophe);
        line("  PS:  {p}", PortrayString.normal_with_triple_quotation_mark);
        line("  RA:  {p}", PortrayString.raw_with_apostrophe);
        line("  RQ:  {p}", PortrayString.raw_with_quotation_mark);
        line("  RC:  {p}", PortrayString.raw_with_triple_apostrophe);
        line("  RS:  {p}", PortrayString.raw_with_triple_quotation_mark);

        line("End of dump of PortrayString");
    }
}


final class             PortrayString_Backslash
    extends             PortrayString
//  extends             Gem_Object <Inspection>
    implements          Inspectable<Inspection>//,
{
    private static final Inspection     inspection = Inspection.create("PortrayString_Backslash");


    //
    //  Members
    //
    private final String                prefix;
    private final String                suffix;
    private final AsciiTable[]          table;


    //
    //  Constructor & Factory
    //
    private                             PortrayString_Backslash(
            final String                        abbreviation,
            final String                        prefix,
            final String                        suffix,
            final AsciiTable[]                  table//,
        )
    {
        super(abbreviation);

        this.prefix = prefix;
        this.suffix = suffix;
        this.table  = table;
    }


    public static final PortrayString_Backslash     create__ALLY__PortrayString(
            final String                        abbreviation,
            final String                        prefix,
            final String                        suffix,
            final AsciiTable[]                  table//,
        )
    {
        final Zone                      z = Zone.current_zone();

        final String                    interned_prefix = z.intern_permenant_string(prefix);
        final String                    interned_suffix = z.intern_permenant_string(suffix);

        return new PortrayString_Backslash(abbreviation, interned_prefix, interned_suffix, table);
    }


    //
    //  Interface Inspectable
    //
    @Override
    public final Inspection             inspect()
    {
        return /*static*/ this.inspection;
    }


    public final void                   portray(final Gem_StringBuilder builder)
    {
        this.portray_prefix(builder);

        builder.append(" ");
        builder.java_quote(this.prefix);
        builder.append("; ");
        builder.java_quote(this.suffix);
        builder.append(">");
    }


    //
    //  Abstract PortrayString
    //
    public final void                   portray_string(final Gem_StringBuilder builder, final String s)
    {
        final AsciiTable[]              table = this.table;

        builder.append(this.prefix);

        /*:*/ int                       begin = 0;
        final int                       total = s.length();

        for (/*:*/ int                  i = 0; i < total; /*  i is incremented in the loop by 1 or 2  */) {
            final int                   code_point = s.codePointAt(i);

            if (code_point > 128) {
                i += Character.charCount(code_point);
                continue;
            }

            final AsciiTable            ascii = table[code_point];

            if (ascii.is_boring_printable) {
                i ++;
                continue;
            }

            if (begin < i) {
                builder.append_slice(s, begin, i);
            }

            i ++;
            begin = i;

            builder.append(ascii.portray_0);
        }

        if (begin < total) {
            builder.append_slice(s, begin);
        }

        builder.append(this.suffix);
    }
}


final class             PortrayString_Normal
    extends             PortrayString
//  extends             Gem_Object <Inspection>
    implements          Inspectable<Inspection>//,
{
    private static final Inspection     inspection = Inspection.create("PortrayString_Normal");


    //
    //  Members
    //
    private final String                prefix;
    private final String                suffix;


    //
    //  Constructor & Factory
    //
    protected                           PortrayString_Normal(
            final String                        abbreviation,
            final String                        prefix,
            final String                        suffix//,
        )
    {
        super(abbreviation);

        this.prefix = prefix;
        this.suffix = suffix;
    }


    public static final PortrayString_Normal    create__ALLY__PortrayString(
            final String                        abbreviation,
            final String                        prefix,
            final String                        suffix//,
        )
    {
        final Zone                      z = Zone.current_zone();

        final String                    interned_prefix = z.intern_permenant_string(prefix);
        final String                    interned_suffix = z.intern_permenant_string(suffix);

        return new PortrayString_Normal(abbreviation, interned_prefix, interned_prefix);
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
        this.portray_prefix(builder);

        builder.append(" ");
        builder.java_quote(this.prefix);
        builder.append("; ");
        builder.java_quote(this.suffix);
        builder.append(">");
    }


    //
    //  Abstract PortrayString
    //
    public final void                   portray_string(final Gem_StringBuilder builder, final String s)
    {
        builder.append(this.prefix, s, this.suffix);
    }


    @Override
    public final String                 portray_string(final String s)
    {
        return this.prefix + s + this.suffix;
    }


}

final class             PortrayString_TripleWithBackslash
    extends             PortrayString
//  extends             Gem_Object <Inspection>
    implements          Inspectable<Inspection>//,
{
    private static final Inspection     inspection = Inspection.create("PortrayString_TripleWithBackslash");


    //
    //  Members
    //
    private final String                prefix;
    private final AsciiTable[]          table;
    private final int                   quote_code_point;
    private final BuildStringState      first_state;
    private final BuildStringState      normal_state;


    //
    //  Constructor & Factory
    //
    private                             PortrayString_TripleWithBackslash(
            final String                        abbreviation,
            final String                        prefix,
            final AsciiTable[]                  table,
            final int                           quote_code_point,
            final BuildStringState              first_state,
            final BuildStringState              normal_state//
        )
    {
        super(abbreviation);

        this.prefix           = prefix;
        this.table            = table;
        this.quote_code_point = quote_code_point;
        this.first_state      = first_state;
        this.normal_state     = normal_state;
    }


    public static final PortrayString_TripleWithBackslash   create__ALLY__PortrayString(
            final String                        abbreviation,
            final String                        prefix,
            final AsciiTable[]                  table,
            final int                           quote_code_point,
            final BuildStringState              first_state,
            final BuildStringState              normal_state//
        )
    {
        final Zone                      z = Zone.current_zone();

        final String                    interned_prefix = z.intern_permenant_string(prefix);

        return new PortrayString_TripleWithBackslash(
                abbreviation,
                interned_prefix,
                table,
                quote_code_point,
                first_state,
                normal_state//,
            );
    }


    //
    //  Interface Inspectable
    //
    @Override
    public final Inspection             inspect()
    {
        return /*static*/ this.inspection;
    }


    public final void                   portray(final Gem_StringBuilder builder)
    {
        this.portray_prefix(builder);

        String                          table_name = "?";

        if (this.table == AsciiTable.table_with_boring_apostrophe) {
            table_name = "table_with_boring_apostrophe";
        } else {
            assert fact( this.table == AsciiTable.table_with_boring_quotation_mark,
                        "this.table == AsciiTable.table_with_boring_quotation_mark");

            table_name = "table_with_boring_quotation_mark";
        }

        String                          quote_code_point_name = "?";

        if (this.quote_code_point == 34) {
            quote_code_point_name = "'\"'";
        } else {
            quote_code_point_name = "\"'\"";
        }

        builder.append(" ");
        builder.java_quote(this.prefix);
        builder.append(" ", table_name, " ", quote_code_point_name, " ", this.first_state.name, " ", this.normal_state.name, ">");
    }


    //
    //  Abstract PortrayString
    //
    public final void                   portray_string(final Gem_StringBuilder builder, final String s)
    {
        builder.append(this.prefix);

        final BuildStringState          normal_state     = this.normal_state;
        final int                       quote_code_point = this.quote_code_point;
        final AsciiTable[]              table            = this.table;

        final BuildStringState          state_1 = normal_state.add;

        /*:*/ int                       begin       = 0;
        /*:*/ BuildStringState          state       = this.first_state;
        /*:*/ int                       state_begin = -7;                   //  Only used when `state != normal_state`
        final int                       total       = s.length();

        for (/*:*/ int                  i = 0; i < total; /*  i is incremented in the loop by 1 or 2  */) {
            final int                   code_point = s.codePointAt(i);

            if (code_point >= 128) {
                i += Character.charCount(code_point);
                state = normal_state;

                continue;
            }

            final AsciiTable            ascii = table[code_point];

            if (ascii.is_boring_printable) {
                i ++;
                state = normal_state;

                continue;
            }

            if (code_point == quote_code_point) {
                if (state == normal_state) {
                    state       = state_1;
                    state_begin = i;
                    i ++;
                    continue;
                }

                i ++;

                final String        add_flush_0 = state.add_flush_0;

                if (add_flush_0 != null) {
                    if (begin < state_begin) {
                        builder.append_slice(s, begin, state_begin);
                    }

                    builder.append(add_flush_0);

                    begin =
                        state_begin = i;
                }

                state = state.add;

                continue;
            }

            if (begin < i) {
                builder.append_slice(s, begin, i);
            }

            i ++;
            begin = i;

            builder.append(ascii.portray_0);
            state = normal_state;
        }

        if (state == normal_state) {
            if (begin < total) {
                builder.append_slice(s, begin);
            }
        } else {
            if (begin < state_begin) {
                builder.append_slice(s, begin, state_begin);
            }
        }

        builder.append(state.finish);
    }
}
