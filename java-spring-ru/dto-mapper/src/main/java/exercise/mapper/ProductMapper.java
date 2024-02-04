package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

@Mapper(
            nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE,
            componentModel = MappingConstants.ComponentModel.SPRING,
            unmappedTargetPolicy = ReportingPolicy.IGNORE
    )
    public abstract class ProductMapper {

        @Mapping(target = "price", source = "cost")
        @Mapping(target = "vendorCode", source = "barcode")
        @Mapping(target = "title", source = "name")
        public abstract ProductDTO map(Product product);

        @InheritInverseConfiguration
        public abstract Product productDTOToProduct(ProductDTO productDTO);

        @Mapping(target = "cost", source = "price")
        public abstract void productUpdateDTOToProduct(ProductUpdateDTO productUpdateDTO,
                                                                   @MappingTarget Product product);

        @Mapping(target = "cost", source = "price")
        @Mapping(target = "barcode", source = "vendorCode")
        @Mapping(target = "name", source = "title")
        public abstract Product productCreateDTOtoProduct(ProductCreateDTO productCreateDTO);

}
