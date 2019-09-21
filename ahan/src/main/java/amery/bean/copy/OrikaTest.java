package amery.bean.copy;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringJUnit4ClassRunner.class)
public class OrikaTest{

    static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    public static void main(String[] args) {
        givenSrcAndDest_whenMaps_thenCorrect();
        givenSrcAndDest_whenMapsUsingBoundMapper_thenCorrect();
    }

    @Test
    public static void givenSrcAndDest_whenMaps_thenCorrect() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Source.class, Dest.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Source src = new Source("Baeldung", 10);
        Dest dest = mapper.map(src, Dest.class);

        assertEquals(dest.getAge(), src.getAge());
        assertEquals(dest.getName(), src.getName());
    }

    @Test
    public static void givenSrcAndDest_whenMapsUsingBoundMapper_thenCorrect() {
        BoundMapperFacade<Source, Dest> boundMapper = mapperFactory.getMapperFacade(Source.class, Dest.class);
        Source src = new Source("baeldung", 10);
        Dest dest = boundMapper.map(src);

        assertEquals(dest.getAge(), src.getAge());
        assertEquals(dest.getName(), src.getName());
    }
}
